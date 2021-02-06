package co.com.ceiba.mobile.pruebadeingreso.repositories;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.businessLogic.IPostBL;
import co.com.ceiba.mobile.pruebadeingreso.db.AppDatabase;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.db.PostDB;
import co.com.ceiba.mobile.pruebadeingreso.rest.PostServices;
import co.com.ceiba.mobile.pruebadeingreso.util.Mapper;
import io.reactivex.Observable;

public class PostRepository implements IPostBL {

    private PostServices postServices;
    private AppDatabase appDatabase;

    @Inject
    public PostRepository(PostServices postServices, AppDatabase appDatabase) {
        this.postServices = postServices;
        this.appDatabase = appDatabase;
    }

    @Override
    public Observable<List<Post>> getPost(String id) {
        return postServices.getPosts(id);
    }

    @Override
    public List<Post> getPostDB(String userId){
        List<PostDB> postDB = appDatabase.postDao().getPostByUser(userId);
        return Mapper.convertListPostDBToPostDomain(postDB);
    }

    @Override
    public void addPostsDB(List<Post> posts){
        List<PostDB> postDBS = Mapper.convertListPostToPostDB(posts);
        appDatabase.postDao().insertPostByUser(postDBS);
    }
}
