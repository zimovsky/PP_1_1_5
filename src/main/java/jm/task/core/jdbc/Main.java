package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 31);
        userService.saveUser("Ivan", "Ivanov", (byte) 32);
        userService.saveUser("Ivan", "Ivanov", (byte) 33);
        userService.saveUser("Ivan", "Ivanov", (byte) 34);
        userService.removeUserById((byte) 2);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();

        users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.dropUsersTable();
    }
}
