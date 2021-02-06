package co.com.ceiba.mobile.pruebadeingreso.models.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PostDao {

    @Query("SELECT * FROM PostDB WHERE userid = :userId ")
    List<PostDB> getPostByUser(String userId);

    @Insert
    void insertPostByUser(List<PostDB> postDBS);


}
