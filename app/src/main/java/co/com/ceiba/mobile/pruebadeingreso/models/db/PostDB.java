package co.com.ceiba.mobile.pruebadeingreso.models.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PostDB {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "userid")
    public String userId;

    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "body")
    public String body;

}
