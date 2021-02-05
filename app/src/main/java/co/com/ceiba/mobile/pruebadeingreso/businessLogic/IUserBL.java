package co.com.ceiba.mobile.pruebadeingreso.businessLogic;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.models.db.UserDB;
import io.reactivex.Observable;

public interface IUserBL {

    Observable<List<User>> getService();
    List<User> getUsersDB();
    void setUsers(List<User> users);

}
