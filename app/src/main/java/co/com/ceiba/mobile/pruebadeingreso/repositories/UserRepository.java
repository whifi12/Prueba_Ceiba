package co.com.ceiba.mobile.pruebadeingreso.repositories;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.businessLogic.IUserBL;
import co.com.ceiba.mobile.pruebadeingreso.rest.UserServices;

public class UserRepository implements IUserBL {

    private UserServices userServices;

    @Inject
    public UserRepository(UserServices userServices){
        this.userServices = userServices;
    }

}
