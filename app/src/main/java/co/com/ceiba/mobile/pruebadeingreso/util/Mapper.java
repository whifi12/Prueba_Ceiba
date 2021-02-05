package co.com.ceiba.mobile.pruebadeingreso.util;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.models.db.UserDB;

public class Mapper {

    public static List<User> convertListUserDBToUserDomain(List<UserDB> usersDB){
        List<User> users = new ArrayList<>();
        for (UserDB userDB : usersDB) {
            User user = new User();
            user.setId(userDB.id);
            user.setName(userDB.name);
            user.setUsername(userDB.username);
            user.setEmail(userDB.email);
            users.add(user);
        }
        return users;
    }

    public static List<UserDB> convertListUserToUserDB(List<User> users){
        List<UserDB> usersdb = new ArrayList<>();
        for (User user : users) {
            UserDB userDB = new UserDB();
            userDB.id = user.getId();
            userDB.name = user.getName();
            userDB.username = user.getUsername();
            userDB.email = user.getEmail();
            usersdb.add(userDB);
        }
        return usersdb;
    }

}
