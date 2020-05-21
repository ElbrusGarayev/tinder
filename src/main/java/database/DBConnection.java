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
                        "jdbc:postgresql://ec2-34-197-188-147.compute-1.amazonaws.com:5432/deo8n2r3hvuml8",
                        "vfqqrouexuyann",
                        "2d557a8aab585f428b1f6b2e0283fa11ed22451e77f633982a79da05cff7c44d"
                );
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}
