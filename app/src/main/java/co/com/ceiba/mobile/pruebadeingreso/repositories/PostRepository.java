package co.com.ceiba.mobile.pruebadeingreso.repositories;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.businessLogic.IPostBL;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.rest.PostServices;
import io.reactivex.Observable;

public class PostRepository implements IPostBL {

    private PostServices postServices;

    @Inject
    public PostRepository(PostServices postServices) {
        this.postServices = postServices;
    }

    @Override
    public Observable<List<Post>> getPost(String id) {
        return postServices.getPosts(id);
    }
}
