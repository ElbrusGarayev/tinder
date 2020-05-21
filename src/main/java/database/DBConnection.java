package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    public static Connection get() {
        if (conn == null) {
            try{
                conn = DriverManager.getConnection(
                        "jdbc:postgresql://ec2-35-171-31-33.compute-1.amazonaws.com/deoq2dpomonefu",
                        "khcilykbviawgk",
                        "11db556325dc247da7a87795d80094e9fd489f8be2addbd2b751cd15dd2b2510"
                );
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}
