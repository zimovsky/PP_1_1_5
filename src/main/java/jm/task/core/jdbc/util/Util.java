package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Util {

    // Hibernate configuration
    private static final SessionFactory sessionFactory;
    static {
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/jdbc_hibernate");
            prop.setProperty("hibernate.connection.username", "root");
            prop.setProperty("hibernate.connection.password", "J[etyyjCkj;ysqGfhjkm");
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("hibernate.hbm2ddl.auto", "update");

            sessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory()
            ;
        }
        catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/jdbc_hibernate";
    private static final String user = "root";
    private static final String password = "J[etyyjCkj;ysqGfhjkm";

    // JDBC variables for opening and managing connection
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void update(String query) {
        try {
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                PreparedStatement statement = connection.prepareStatement(query);
                int affectedRows = statement.executeUpdate();
                System.out.println("UPDATE: " + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users;");
                ResultSet resultSet = statement.executeQuery();
                System.out.println("QUERY: SELECT * FROM users;");
                while (resultSet.next()) {
                    if (!resultSet.isBeforeFirst() & !resultSet.isAfterLast()) {
                        users.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
                    }
                }

                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


}
