package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    public static Connection get() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(
                    "ec2-35-171-31-33.compute-1.amazonaws.com",
                    "khcilykbviawgk",
                    "11db556325dc247da7a87795d80094e9fd489f8be2addbd2b751cd15dd2b2510"
            );
        }
        return conn;
    }
}
