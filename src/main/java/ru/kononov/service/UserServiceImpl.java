package ru.kononov.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kononov.dao.UserDAOImpl;
import ru.kononov.entities.User;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAOImpl dao;
    @Override
    public User createUser(User user) {
        if(getUserByName(user.getName()) == null )
        {
            try {
                return dao.createUser(user);
            } catch (Exception e) {
                System.out.println("Ошибка создания пользователя.");
            }
        }
        else {
            System.out.println("Уже существует пользователь с именем " + user.getName());
        }
        return null;
    }

    @Override
    public User getUserById(int userId) {

        try{
            return dao.getUserById(Long.valueOf(userId));
        }
        catch (Exception e) {
            System.out.println("Не удалось найти пользователя с id " + userId);
            return null;
        }
    }

    @Override
    public User getUserByName(String userName) {
        try{
            return dao.getUserByName(userName);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public void deleteUser(int userId) {
        dao.deleteUser(Long.valueOf(userId));
    }
}
