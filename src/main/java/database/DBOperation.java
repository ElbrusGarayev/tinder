package database;

import entity.Like;
import entity.Message;
import entity.User;
import heroku.HerokuEnv;
import lombok.SneakyThrows;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBOperation {
    private static Connection conn = DBConnection.get();
//    private static Connection conn = DBConnection.getFromURL(HerokuEnv.jdbc_url());
    private static SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");

    @SneakyThrows
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "select * from users";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            users.add(new User(id, email, password));
        }
        return users;
    }

    @SneakyThrows
    public static List<User> getLikedUsers(int userId) {
        List<User> users = new ArrayList<>();
        String query = "select  *  from users where id  " +
                "in(select whom from likes where who = ? and status = true)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String status = resultSet.getString("status");
            String password = resultSet.getString("password");
            String url = resultSet.getString("url");
            String lastSeen = resultSet.getString("lastSeen");
            lastSeen = lastSeen.substring(0, 16);
            users.add(new User(id, email, name, surname, status, password, url, lastSeen));
        }
        return users;
    }

    @SneakyThrows
    public static void insertUser(User user) {
        String query = "insert into users (email, name, surname, status, password, url, lastseen) " +
                "values (?, ?, ?, ?, ?, ?, localtimestamp)";
        PreparedStatement stmt = conn.prepareStatement(query);
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
        List<Like> likes = new ArrayList<>();
        String query = "select * from likes";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            int whoId = resultSet.getInt("who");
            int whomId = resultSet.getInt("whom");
            boolean status = resultSet.getBoolean("status");
            likes.add(new Like(whoId, whomId, status));
        }
        return likes;
    }

    @SneakyThrows
    public static void insertLike(Like like) {
        String QUERY = String.format("insert into likes values (default, %d, %d, %b)", like.getWho(),
                like.getWhom(), like.isStatus());
        PreparedStatement stmt = conn.prepareStatement(QUERY);
        stmt.execute();
    }

    @SneakyThrows
    public static List<User> getUsersToDisplay(int userId) {
        List<User> users = new ArrayList<>();
        String query = "select distinct  u.*  from users u  where" +
                " u.id not in(select whom from likes where who = ?) and " +
                " u.id != ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setInt(2, userId);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String status = resultSet.getString("status");
            String password = resultSet.getString("password");
            String url = resultSet.getString("url");
            users.add(new User(id, email, name, surname, status, password, url));
        }
        return users;
    }

    @SneakyThrows
    public static void updateLastSeen(int id) {
        String query = "update users set lastseen = localtimestamp where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.execute();
    }

    @SneakyThrows
    public static User getUser(int id) {
        String query = "select * from users where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            int uid = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String status = resultSet.getString("status");
            String password = resultSet.getString("password");
            String url = resultSet.getString("url");
            String lastseen = resultSet.getString("lastseen");
            return new User(uid, email, name, surname, status, password, url, lastseen);
        }
        return new User();
    }

    @SneakyThrows
    public static List<Message> getAllMessages(int sender, int receiver) {
        List<Message> messages = new ArrayList<>();
        String query = "select * from messages " +
                "where (sender = ? and receiver = ?) " +
                "or (sender = ? and receiver = ?)\n" +
                "order by id asc";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, sender);
        stmt.setInt(2, receiver);
        stmt.setInt(3, receiver);
        stmt.setInt(4, sender);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            int from = resultSet.getInt("sender");
            int to = resultSet.getInt("receiver");
            String content = resultSet.getString("content");
            Timestamp datetime = resultSet.getTimestamp("datetime");
            messages.add(new Message(from, to, content, formatter.format(datetime)));
        }
        return messages;
    }

    @SneakyThrows
    public static void insertMessage(Message message) {
        String QUERY = "insert into messages values (default, ?, ?, localtimestamp, ?)";
        PreparedStatement stmt = conn.prepareStatement(QUERY);
        stmt.setInt(1, message.getSender());
        stmt.setInt(2, message.getReceiver());
        stmt.setString(3, message.getContent());
        stmt.execute();
    }
}
