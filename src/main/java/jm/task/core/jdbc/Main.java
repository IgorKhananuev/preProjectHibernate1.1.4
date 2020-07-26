package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBCConnection = new UserDaoJDBCImpl();
        userDaoJDBCConnection.createUsersTable();
        userDaoJDBCConnection.saveUser("Q", "W", (byte) 1);
        userDaoJDBCConnection.saveUser("W", "E", (byte) 2);
        userDaoJDBCConnection.saveUser("E", "R", (byte) 3);
        userDaoJDBCConnection.saveUser("T", "Y", (byte) 4);
        System.out.println(userDaoJDBCConnection.getAllUsers());
        userDaoJDBCConnection.cleanUsersTable();
        userDaoJDBCConnection.dropUsersTable();
    }
}