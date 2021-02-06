package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.PostRecyclerAdapter;
import co.com.ceiba.mobile.pruebadeingreso.adapter.UserRecyclerAdapter;
import co.com.ceiba.mobile.pruebadeingreso.base.BaseActivity;
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.util.Constants;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.MainViewModel;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.PostViewModel;

public class PostActivity extends BaseActivity {


    private ActivityPostBinding binding;
    private PostViewModel postViewModel;
    private List<Post> posts;
    private PostRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadActivity();
        getUserData();
        loadRecycler();
        listenerObservable();
        loadService();
    }

    private void loadService(){
        postViewModel.loadPosts(getIntent().getStringExtra(Constants.ID));
    }

    private void listenerObservable() {
        postViewModel.getPosts().observe(this, posts -> {
            this.posts = posts;
            adapter.setItems(posts);
        });
        postViewModel.getProgress().observe(this, progress -> {
            if(progress){
                showProgressDIalog(R.string.generic_message_progress);
            }else{
                dismissProgressDialog();
            }
        });
        postViewModel.getError().observe(this,error ->{
            showAlertDialogTryAgain(error.getTitle(),error.getText());
        });
    }

    private void loadActivity(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_post);
        configureDagger();
        createProgressDialog();
        postViewModel = ViewModelProviders.of(this,viewModelFactory).get(PostViewModel.class);
        binding.setViewModel((PostViewModel) postViewModel);
    }

    private void loadRecycler() {
        posts = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewPostsResults.setAdapter(adapter);
        binding.recyclerViewPostsResults.setLayoutManager(linearLayoutManager);
        adapter = new PostRecyclerAdapter(PostActivity.this, posts);
        binding.recyclerViewPostsResults.getAdapter();
        binding.recyclerViewPostsResults.setAdapter(adapter);
    }

    private void getUserData(){
        binding.name.setText(getIntent().getStringExtra(Constants.NAME));
        binding.phone.setText(getIntent().getStringExtra(Constants.PHONE));
        binding.email.setText(getIntent().getStringExtra(Constants.EMAIL));
    }

    private void showAlertDialogTryAgain(final int title, final int message) {
        runOnUiThread(() -> {
            showAlertDialog(title, message, false, R.string.try_again, (dialogInterface, i) -> {
                loadService();
            }, R.string.cancel , (dialogInterface, i) -> onBackPressed(), null);
        });
    }



}
