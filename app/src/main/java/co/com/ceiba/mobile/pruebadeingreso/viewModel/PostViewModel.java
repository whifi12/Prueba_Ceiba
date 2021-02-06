package co.com.ceiba.mobile.pruebadeingreso.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.businessLogic.IPostBL;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.repositories.PostRepository;
import co.com.ceiba.mobile.pruebadeingreso.util.Error;
import co.com.ceiba.mobile.pruebadeingreso.util.IValidateInternet;
import co.com.ceiba.mobile.pruebadeingreso.util.ValidateInternet;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {

    private IPostBL postBL;
    private CompositeDisposable disposables;
    private MutableLiveData<List<Post>> posts;
    private MutableLiveData<Boolean> progress;
    private IValidateInternet validateInternet;
    private MutableLiveData<Error> error;

    @Inject
    public PostViewModel(PostRepository postBL, ValidateInternet validateInternet) {
        this.postBL = postBL;
        this.validateInternet = validateInternet;
        this.disposables = new CompositeDisposable();
        this.posts = new MutableLiveData<>();
        this.progress = new MutableLiveData<>();
        this.error = new MutableLiveData<>();
    }

    public void loadPosts(String id){
        try {
            progress.setValue(true);
            List<Post> posts = postBL.getPostDB(id);
            validateService(posts,id);
        }catch (Exception e){
            error.setValue(new Error(R.string.error_title,R.string.generic_error));
        }
    }

    public void getPost(String id){
        Observable<List<Post>> response = postBL.getPost(id);
        Disposable disposable = response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Post>>() {
                    @Override
                    public void onNext(@NonNull List<Post> posts) {
                        loadData(posts);
                        saveData(posts);
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

    public void validateInternetToGetPost(String id) {
        if(validateInternet.isConnected()) {
           getPost(id);
        }else{
            error.setValue(new Error(R.string.error_title,R.string.internet_error));
        }
    }

    public void loadData(List<Post> posts){
        this.posts.setValue(posts);
    }

    public void validateService(List<Post> posts,String id){
        if(posts.isEmpty()){
            validateInternetToGetPost(id);
        }else{
            this.posts.setValue(posts);
            progress.setValue(false);
        }
    }

    public void saveData(List<Post> posts) {
        try {
            postBL.addPostsDB(posts);
        }catch (Exception e){
            Log.e(e.getLocalizedMessage(),e.getMessage());
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

    public MutableLiveData<List<Post>> getPosts() {
        return posts;
    }

    public MutableLiveData<Boolean> getProgress() {
        return progress;
    }

    public MutableLiveData<Error> getError() {
        return error;
    }
}
