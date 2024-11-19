package ru.kononov;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.kononov.dao.UserDAO;
import ru.kononov.dao.UserDAOImpl;
import ru.kononov.entities.User;
import ru.kononov.service.UserService;
import ru.kononov.service.UserServiceImpl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("ru.kononov");
        UserService userService = ctx.getBean(UserServiceImpl.class);
        System.out.println(userService.getAllUsers());
        User newUser = userService.createUser(new User("alex"));
        System.out.println(userService.getAllUsers());
        userService.deleteUser(newUser.getId());
        System.out.println(userService.getAllUsers());


    }
}