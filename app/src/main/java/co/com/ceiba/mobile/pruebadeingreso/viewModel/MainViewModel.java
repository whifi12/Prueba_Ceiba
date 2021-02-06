package co.com.ceiba.mobile.pruebadeingreso.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.util.Error;
import co.com.ceiba.mobile.pruebadeingreso.util.IValidateInternet;
import co.com.ceiba.mobile.pruebadeingreso.util.ValidateInternet;
import io.reactivex.Observable;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.businessLogic.IUserBL;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.repositories.UserRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private IUserBL userRepository;
    private CompositeDisposable disposables;
    private MutableLiveData<List<User>> users;
    private MutableLiveData<Boolean> progress;
    private IValidateInternet validateInternet;
    private MutableLiveData<Error> error;

    @Inject
    public MainViewModel(UserRepository userRepository, ValidateInternet validateInternet) {
        this.userRepository = userRepository;
        this.validateInternet = validateInternet;
        this.disposables = new CompositeDisposable();
        this.users = new MutableLiveData<>();
        this.progress = new MutableLiveData<>();
        this.error = new MutableLiveData<>();
    }

    public void loadUsers(){
        try {
            progress.setValue(true);
            List<User> users = userRepository.getUsersDB();
            validateService(users);
        }catch (Exception e){
            error.setValue(new Error(R.string.error_title,R.string.generic_error));
            progress.setValue(false);
        }

    }

    public void getUsersService(){
        Observable<List<User>> response = userRepository.getService();
        Disposable disposable = response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<User>>() {
                    @Override
                    public void onNext(@NonNull List<User> users) {
                        loadData(users);
                        saveData(users);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        error.setValue(new Error(R.string.error_title,R.string.generic_error));
                    }

                    @Override
                    public void onComplete() {
                        progress.setValue(false);
                    }
                });
        disposables.add(disposable);
    }

    public void validateInternetToGetUsers(){
        if(validateInternet.isConnected()){
            getUsersService();
        }else {
            error.setValue(new Error(R.string.error_title,R.string.internet_error));
        }
    }

    public void saveData(List<User> users) {
        try {
            userRepository.setUsers(users);
        }catch (Exception e){
            Log.e(e.getLocalizedMessage(),e.getMessage());
        }
    }

    public void validateService(List<User> users){
        if(users.isEmpty()){
            validateInternetToGetUsers();
        }else{
            this.users.setValue(users);
            progress.setValue(false);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

    public MutableLiveData<Boolean> getProgress() {
        return progress;
    }

    public void loadData(List<User> users){
        this.users.setValue(users);
    }

    public MutableLiveData<List<User>> getList(){
        return users;
    }

    public MutableLiveData<Error> getError() {
        return error;
    }
}
