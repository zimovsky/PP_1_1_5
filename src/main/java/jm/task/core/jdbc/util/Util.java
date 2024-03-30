package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Util {
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
