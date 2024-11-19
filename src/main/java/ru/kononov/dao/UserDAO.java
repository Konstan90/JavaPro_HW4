package ru.kononov.dao;

import ru.kononov.entities.User;

import java.util.List;

public interface UserDAO {
    User createUser(User user);
    User getUserById(Long userId);
    User getUserByName(String userName);
    List<User> getAllUsers();
    void deleteUser(Long userId);
}
