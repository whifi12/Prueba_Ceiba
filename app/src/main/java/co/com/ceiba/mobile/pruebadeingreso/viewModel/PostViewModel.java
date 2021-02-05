package co.com.ceiba.mobile.pruebadeingreso.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.businessLogic.IPostBL;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.repositories.PostRepository;
import co.com.ceiba.mobile.pruebadeingreso.repositories.UserRepository;
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

    @Inject
    public PostViewModel(PostRepository postBL) {
        this.postBL = postBL;
        this.disposables = new CompositeDisposable();
        this.posts = new MutableLiveData<>();
        this.progress = new MutableLiveData<>();
    }

    public void getPost(String id){
        progress.setValue(true);
        Observable<List<Post>> response = postBL.getPost(id);
        Disposable disposable = response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Post>>() {
                    @Override
                    public void onNext(@NonNull List<Post> posts) {
                        loadData(posts);
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

    public void loadData(List<Post> posts){
        this.posts.setValue(posts);
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
}
