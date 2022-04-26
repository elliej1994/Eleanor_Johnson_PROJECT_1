package dev.johnson.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {



    public static Connection createConnection(){
    Connection conn;
        try {

            conn = DriverManager.getConnection(System.getenv("PROJECTDB"));

        } catch (SQLException e) {;
            throw new Error("bad connection am I going through a tunnel?",e);
        }
        return conn;
    }
}


