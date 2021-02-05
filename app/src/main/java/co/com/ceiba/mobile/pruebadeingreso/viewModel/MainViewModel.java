package co.com.ceiba.mobile.pruebadeingreso.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
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

    @Inject
    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.disposables = new CompositeDisposable();
        this.users = new MutableLiveData<>();
        this.progress = new MutableLiveData<>();
    }

    public void loadUsers(){
        try {
            progress.setValue(true);
            List<User> users = userRepository.getUsersDB();
            validateService(users);
        }catch (Exception e){
            Log.e(e.getLocalizedMessage(),e.getMessage());
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
                        Log.e(e.getLocalizedMessage(),e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        progress.setValue(false);
                    }
                });
        disposables.add(disposable);
    }

    private void saveData(List<User> users) {
        try {
            userRepository.setUsers(users);
        }catch (Exception e){
            Log.e(e.getLocalizedMessage(),e.getMessage());
        }
    }

    public void validateService(List<User> users){
        if(users.isEmpty()){
            getUsersService();
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

    private void loadData(List<User> users){
        this.users.setValue(users);
    }

    public MutableLiveData<List<User>> getList(){
        return users;
    }

}
