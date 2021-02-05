package co.com.ceiba.mobile.pruebadeingreso.rest;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostServices {

    @GET(Endpoints.GET_POST_USER)
    Observable<List<Post>> getPosts(@Query("userId") String userId);
}
