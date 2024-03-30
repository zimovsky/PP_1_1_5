package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    ResultSet resultSet;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Util.update("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), lastName VARCHAR(255), age TINYINT);");
    }

    public void dropUsersTable() {
        Util.update("DROP TABLE IF EXISTS users;");
    }

    public void saveUser(String name, String lastName, byte age) {
        Util.update("INSERT INTO users (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', '" + age + "');");
    }

    public void removeUserById(long id) {
        Util.update("DELETE FROM users WHERE id = " + id + ";");
    }

    public List<User> getAllUsers() {
        return Util.getAllUsers();
    }

    public void cleanUsersTable() {
        Util.update("DELETE FROM users;");
    }
}
