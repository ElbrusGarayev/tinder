package database;

import heroku.HerokuEnv;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    public static Connection get() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(
                        HerokuEnv.jdbc_url(),
                        HerokuEnv.jdbc_username(),
                        HerokuEnv.jdbc_password()
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    @SneakyThrows
    public static Connection getFromURL(String jdbc_url) {
        return DriverManager.getConnection(jdbc_url);
    }
}
