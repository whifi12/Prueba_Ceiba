package co.com.ceiba.mobile.pruebadeingreso.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;


import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.UserRecyclerAdapter;
import co.com.ceiba.mobile.pruebadeingreso.base.BaseActivity;
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.util.Constants;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.MainViewModel;


public class MainActivity extends BaseActivity implements UserRecyclerAdapter.OnUsersListener,TextWatcher {


     private MainViewModel mainViewModel;
     private ActivityMainBinding binding;
     private List<User> users;
     private UserRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadActivity();
        loadRecycler();
        search();
        listenerObsevable();
        loadService();
    }

    private void loadActivity(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        configureDagger();
        createProgressDialog();
        mainViewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel.class);
        binding.setViewModel((MainViewModel) mainViewModel);
    }

    private void search(){
       binding.editTextSearch.addTextChangedListener(this);
    }

    private void listenerObsevable() {
        mainViewModel.getList().observe(this, users -> {
            this.users = users;
            adapter.setItems(users);
        });
        mainViewModel.getProgress().observe(this,progress -> {
           if(progress){
               showProgressDIalog(R.string.generic_message_progress);
           }else{
               dismissProgressDialog();
           }
        });
        mainViewModel.getError().observe(this,error ->{
            showAlertDialogTryAgain(error.getTitle(),error.getText());
        });
    }

    private void loadRecycler() {
        users = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewSearchResults.setAdapter(adapter);
        binding.recyclerViewSearchResults.setLayoutManager(linearLayoutManager);
        adapter = new UserRecyclerAdapter(MainActivity.this, users,this);
        binding.recyclerViewSearchResults.getAdapter();
        binding.recyclerViewSearchResults.setAdapter(adapter);
    }

    private void goToPost(User user){
        Intent intent = new Intent(MainActivity.this ,PostActivity.class);
        intent.putExtra(Constants.ID,user.getId());
        intent.putExtra(Constants.NAME,user.getName());
        intent.putExtra(Constants.EMAIL,user.getEmail());
        intent.putExtra(Constants.PHONE,user.getPhone());
        startActivity(intent);
    }

    private void loadService(){
        mainViewModel.loadUsers();
    }

    private void showAlertDialogTryAgain(final int title, final int message) {
        runOnUiThread(() -> {
            showAlertDialog(title, message, false, R.string.try_again, (dialogInterface, i) -> {
                loadService();
            }, R.string.cancel , (dialogInterface, i) -> onBackPressed(), null);
        });
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(User user) {
        goToPost(user);
    }

    @Override
    public void isEmpty(int view) {
        binding.empty.setVisibility(view);
    }
}