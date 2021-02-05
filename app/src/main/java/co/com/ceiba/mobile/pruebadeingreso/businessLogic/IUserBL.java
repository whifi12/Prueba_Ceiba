package co.com.ceiba.mobile.pruebadeingreso.businessLogic;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.User;
import io.reactivex.Observable;

public interface IUserBL {

    Observable<List<User>> getService();

}
