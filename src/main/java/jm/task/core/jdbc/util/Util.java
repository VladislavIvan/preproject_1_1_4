package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/test";
    static final String username = "root";
    static final String password = "root";
    static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("INFO: Connected successfully");
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger("connect").log(Level.WARNING, "Connection failed...");
        }
        return connection;
    }
}

