package co.com.ceiba.mobile.pruebadeingreso.util;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.models.db.PostDB;
import co.com.ceiba.mobile.pruebadeingreso.models.db.UserDB;

public class Mapper {

    public static List<User> convertListUserDBToUserDomain(List<UserDB> usersDB){
        List<User> users = new ArrayList<>();
        for (UserDB userDB : usersDB) {
            User user = new User();
            user.setId(userDB.id);
            user.setName(userDB.name);
            user.setPhone(userDB.phone);
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
            userDB.phone = user.getPhone();
            userDB.email = user.getEmail();
            usersdb.add(userDB);
        }
        return usersdb;
    }

    public static List<Post> convertListPostDBToPostDomain(List<PostDB> postsDB){
        List<Post> posts = new ArrayList<>();
        for (PostDB postDB: postsDB) {
            Post post = new Post();
            post.setUserId(postDB.userId);
            post.setId(postDB.id);
            post.setTitle(postDB.title);
            post.setBody(postDB.body);
            posts.add(post);
        }
        return posts;
    }

    public static List<PostDB> convertListPostToPostDB(List<Post> posts){
        List<PostDB> postDBS = new ArrayList<>();
        for (Post post : posts) {
            PostDB postDB = new PostDB();
            postDB.userId = post.getUserId();
            postDB.id = post.getId();
            postDB.title = post.getTitle();
            postDB.body = post.getBody();
            postDBS.add(postDB);
        }
        return postDBS;
    }

}
