package co.com.ceiba.mobile.pruebadeingreso.businessLogic;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import io.reactivex.Observable;

public interface IPostBL {

    Observable<List<Post>> getPost(String id);

    List<Post> getPostDB(String userId);

    void addPostsDB(List<Post> posts);

}
