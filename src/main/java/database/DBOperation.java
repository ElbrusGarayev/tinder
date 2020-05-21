package database;

import entity.Like;
import entity.User;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBOperation {
    @SneakyThrows
    public static List<User> getAllUsers()  {
        Connection conn = DBConnection.get();
        String QUERY = "select * from users";
        PreparedStatement stmt = conn.prepareStatement(QUERY);
        ResultSet outcome = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (outcome.next()) {
            int id = outcome.getInt("id");
            String email = outcome.getString("email");
            String password = outcome.getString("password");
            users.add(new User(id, email, password));
        }
        return users;
    }
    @SneakyThrows
    public static List<User> getLikedUsers(int user_id)  {
        Connection conn = DBConnection.get();
        String QUERY = "select  *  from users where id  " +
                "in(select whom from likes where who=" + user_id + " and status = true)";
        PreparedStatement stmt = conn.prepareStatement(QUERY);
        ResultSet outcome = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (outcome.next()) {
            int id = outcome.getInt("id");
            String email = outcome.getString("email");
            String name = outcome.getString("name");
            String surname = outcome.getString("surname");
            String status = outcome.getString("status");
            String password = outcome.getString("password");
            String url = outcome.getString("url");
            String lastSeen = outcome.getString("lastSeen");
            lastSeen = lastSeen.substring(0, 16);
            users.add(new User(id, email, name, surname, status, password, url, lastSeen));
        }
        return users;
    }
    @SneakyThrows
    public static void insertUser(User user)   {
        Connection conn = DBConnection.get();
        String QUERY = "insert into users (email, name, surname, status, password, url, lastseen) " +
                "values (?, ?, ?, ?, ?, ?, localtimestamp)";
        PreparedStatement stmt = conn.prepareStatement(QUERY);
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getName());
        stmt.setString(3, user.getSurname());
        stmt.setString(4, user.getStatus());
        stmt.setString(5, user.getPassword());
        stmt.setString(6, user.getUrl());
        stmt.execute();
    }
    @SneakyThrows
    public static List<Like> getLikes() {
        Connection conn = DBConnection.get();
        String QUERY = "select * from likes";
        PreparedStatement stmt = conn.prepareStatement(QUERY);
        ResultSet outcome = stmt.executeQuery();
        List<Like> likes = new ArrayList<>();
        while (outcome.next()) {
            int whoId = outcome.getInt("who");
            int whomId = outcome.getInt("whom");
            boolean status = outcome.getBoolean("status");
            likes.add(new Like(whoId, whomId, status));
        }
        return likes;
    }
    @SneakyThrows
    public static void insertLike(Like like) {
        Connection conn = DBConnection.get();
        String QUERY = String.format("insert into likes values (default, %d, %d, %b)", like.getWho(),
                like.getWhom(), like.isStatus());
        PreparedStatement stmt = conn.prepareStatement(QUERY);
        stmt.execute();
    }
    @SneakyThrows
    public static List<User> getUsersToDisplay(int user_id) {
        Connection conn = DBConnection.get();
        String QUERY = "select distinct  u.*  from users u  where" +
                " u.id not in(select whom from likes where who = " + user_id + ") and " +
                " u.id<>" + user_id;
        PreparedStatement stmt = conn.prepareStatement(QUERY);
        ResultSet outcome = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (outcome.next()) {
            int id = outcome.getInt("id");
            String email = outcome.getString("email");
            String name = outcome.getString("name");
            String surname = outcome.getString("surname");
            String status = outcome.getString("status");
            String password = outcome.getString("password");
            String url = outcome.getString("url");
            users.add(new User(id, email, name, surname, status, password, url));
        }
        return users;
    }
    @SneakyThrows
    public static void updateLastSeen(int id){
        Connection connection = DBConnection.get();
        String query = "update users set lastseen = localtimestamp where id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.execute();
    }
}
