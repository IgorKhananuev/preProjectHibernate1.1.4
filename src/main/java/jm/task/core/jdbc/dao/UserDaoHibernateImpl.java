package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private Session session;
    List<User> users;
    ;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try {
            Util util = new Util();
            String createUserTableQuery = "CREATE TABLE `users`.`users`" +
                    "(  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(45) NOT NULL," +
                    "  `lastName` VARCHAR(45) NOT NULL," +
                    "  `age` tinyint(4) NOT NULL," +
                    "PRIMARY KEY (`id`))";
            Statement statement = util.getJDBCUtilConnection().createStatement();
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
            String dropUsersTableQuery = "DROP TABLE IF EXISTS `users`.`users`";
            Statement statement = util.getJDBCUtilConnection().createStatement();
            statement.executeUpdate(dropUsersTableQuery);
            statement.close();
        } catch (SQLException sqlException) {
            System.out.println("There is not such table exists");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Util util = new Util();
        session = util.getHibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        util.getHibernateUtilConnection().close();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Util util = new Util();
        session = util.getHibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        User removedUser = (User) session.get(User.class, id);
        session.delete(removedUser);
        transaction.commit();
        util.getHibernateUtilConnection().close();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Util util = new Util();
        session = util.getHibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        users = (List<User>) session.createCriteria(User.class).list();
        transaction.commit();
        session.close();
        util.getHibernateUtilConnection().close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Util util = new Util();
        session = util.getHibernateUtilConnection();
        Transaction transaction = session.beginTransaction();
        List<User> cleanedUserList = (List<User>) session.createCriteria(User.class).list();
        for (User u : cleanedUserList) {
            session.delete(u);
        }
        transaction.commit();
        session.close();
        util.getHibernateUtilConnection().close();
    }
}

