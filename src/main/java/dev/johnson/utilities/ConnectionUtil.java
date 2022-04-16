package dev.johnson.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {



    public static Connection createConnection(){
    Connection conn;
        try {

            conn = DriverManager.getConnection("jdbc:postgresql://johnson-db.cbn3pfkjnp4z.us-east-1.rds.amazonaws.com/project1db?user=postgres&password=!pinkkeyboardwithRBG?");

        } catch (SQLException e) {;
            throw new Error("bad connection am I going through a tunnel?",e);
        }
        return conn;
    }
}

/*
jdbc:postgresql://johnson-db.cbn3pfkjnp4z.us-east-1.rds.amazonaws.com/project1db?user=postgres&password=!pinkkeyboardwithRBG?
*/
