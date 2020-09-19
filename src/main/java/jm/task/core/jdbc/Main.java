package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernateImpl = new UserDaoHibernateImpl();
        userDaoHibernateImpl.createUsersTable();
        userDaoHibernateImpl.saveUser("name1", "lastName1", (byte) 10);
        userDaoHibernateImpl.saveUser("name2", "lastName2", (byte) 20);
        userDaoHibernateImpl.saveUser("name3", "lastName3", (byte) 30);
        userDaoHibernateImpl.saveUser("name4", "LastName4", (byte) 40);
        userDaoHibernateImpl.saveUser("name5", "lastName5", (byte) 50);
        System.out.println(userDaoHibernateImpl.getAllUsers());
        userDaoHibernateImpl.cleanUsersTable();
        userDaoHibernateImpl.dropUsersTable();
    }
}
