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
    public void createUsersTable() {
        try {
            Util util = new Util();
            String createUserTableQuery = "CREATE TABLE `user`.`users`" +
                    "(\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT, \n" +
                    "  `name` VARCHAR(45) NOT NULL, \n" +
                    "  `lastName` VARCHAR(45) NOT NULL, \n" +
                    "  `age` VARCHAR(45) NOT NULL, \n" +
                    "PRIMARY KEY (`id`))";
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(createUserTableQuery);
            statement.close();
        } catch (SQLException sqlException) {
            System.out.println("Such table already exists");
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Util util = new Util();
            String dropUsersTableQuery = "DROP TABLE IF EXISTS `user`.`users`";
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(dropUsersTableQuery);
            statement.close();
        } catch (SQLException sqlException) {
            System.out.println("There is not such table exists");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Util util = new Util();
            String saveUserQuery = ("INSERT INTO `user`.`users` (name, lastName, age) " +
                    "VALUES ('" + name + "', '" + lastName + "', '" + age + "')");
            Statement statement = util.getConnection().createStatement();
            statement.execute(saveUserQuery);
            System.out.println("User with name - " + name + " added to database");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id){
        try {
            Util util = new Util();
            String removeUserByIdQuery = "DELETE FROM `user`.`users` WHERE id= ?";
            PreparedStatement statement = util.getConnection().prepareStatement(removeUserByIdQuery);
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            System.out.println("User with id = " + id +  " not exists in database");
        }
    }

    @Override
    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> listOfUsers = new ArrayList<>();
        try {
            Statement statement = util.getConnection().createStatement();
            {
                if (statement.execute("SELECT * FROM `user`.`users`")) {
                    ResultSet resultSet = statement.getResultSet();
                    while ((resultSet.next())) {
                        listOfUsers.add(new User(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("There are not exist users in database");
        }
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Util util = new Util();
            String clearUsersTableQuery = "TRUNCATE `user`.`users`";
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(clearUsersTableQuery);
            statement.close();
        } catch (SQLException cleanUsersTableSqlException) {
            System.out.println("Existing table is empty");
        }
    }
}
