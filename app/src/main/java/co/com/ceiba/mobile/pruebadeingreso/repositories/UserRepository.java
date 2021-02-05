package co.com.ceiba.mobile.pruebadeingreso.repositories;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.businessLogic.IUserBL;
import co.com.ceiba.mobile.pruebadeingreso.db.AppDatabase;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.models.db.UserDB;
import co.com.ceiba.mobile.pruebadeingreso.rest.UserServices;
import co.com.ceiba.mobile.pruebadeingreso.util.Mapper;
import io.reactivex.Observable;

public class UserRepository implements IUserBL {

    private UserServices userServices;
    private AppDatabase appDatabase;

    @Inject
    public UserRepository(UserServices userServices, AppDatabase appDatabase){
        this.userServices = userServices;
        this.appDatabase = appDatabase;
    }

    @Override
    public Observable<List<User>> getService() {
        return userServices.getUsers();
    }

    @Override
    public List<User> getUsersDB(){
        List<UserDB> userDB = appDatabase.userDao().getAllUsers();
        return Mapper.convertListUserDBToUserDomain(userDB);
    }

    @Override
    public void setUsers(List<User> users){
        List<UserDB> userDBS =  Mapper.convertListUserToUserDB(users);
        appDatabase.userDao().insertUser(userDBS);
    }
}
