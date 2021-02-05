package co.com.ceiba.mobile.pruebadeingreso.repositories;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.businessLogic.IUserBL;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.UserServices;
import io.reactivex.Observable;

public class UserRepository implements IUserBL {

    private UserServices userServices;

    @Inject
    public UserRepository(UserServices userServices){
        this.userServices = userServices;
    }

    @Override
    public Observable<List<User>> getService() {
        return userServices.getUsers();
    }
}
