package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user?serverTimezone=Europe/Minsk&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            /*if (!connection.isClosed()) {
                System.out.println("Connection is successful");
            }
            if (connection.isClosed()) {
                System.out.println("Connection is disconnected");
            }*/
        } catch (SQLException utilSqlException) {
            utilSqlException.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

