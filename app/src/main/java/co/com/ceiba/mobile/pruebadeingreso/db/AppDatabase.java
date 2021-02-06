package co.com.ceiba.mobile.pruebadeingreso.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.ceiba.mobile.pruebadeingreso.models.db.PostDB;
import co.com.ceiba.mobile.pruebadeingreso.models.db.PostDao;
import co.com.ceiba.mobile.pruebadeingreso.models.db.UserDB;
import co.com.ceiba.mobile.pruebadeingreso.models.db.UserDao;

@Database(entities = {UserDB.class,PostDB.class}, version  = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract PostDao postDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
