package co.com.ceiba.mobile.pruebadeingreso.rest;


import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.User;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserServices {

    @GET(Endpoints.GET_USERS)
    Observable<List<User>> getUsers();
}
