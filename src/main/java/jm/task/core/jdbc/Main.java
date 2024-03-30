package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Sergey", "Kosachev", (byte) 33);
        userService.saveUser("Eva", "Pavliuchkova", (byte) 19);
        userService.saveUser("Tim", "XS", (byte) 42);
        userService.saveUser("Yul", "XS", (byte) 21);
        List<User> users = userService.getAllUsers();
        System.out.println(users.size());
        for (User user : users) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        users = userService.getAllUsers();
        System.out.println(users.size());
    }
}
