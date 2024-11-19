package ru.kononov.service;

import ru.kononov.entities.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(int userId);
    User getUserByName(String userName);
    List<User> getAllUsers();
    void deleteUser(int userId);
}
