package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }


    @Override
    public void createUsersTable() throws SQLException {


    }

    @Override
    public void dropUsersTable() throws SQLException {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {

    }

    @Override
    public void removeUserById(long id) throws SQLException {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
