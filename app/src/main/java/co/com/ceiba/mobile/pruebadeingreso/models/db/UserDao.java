package co.com.ceiba.mobile.pruebadeingreso.models.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM userDB")
    List<UserDB> getAllUsers();

    @Insert
    void insertUser(List<UserDB> users);

    @Delete
    void delete(UserDB user);

}
