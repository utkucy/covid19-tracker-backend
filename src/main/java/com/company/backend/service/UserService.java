package com.company.backend.service;

import com.company.backend.dao.UserDao;
import com.company.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//A UserService retrieves data from related API class and send these data to userDao.
@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("postgres-user") UserDao userDao) { this.userDao = userDao; }

    public int addUser(User person) {
        return userDao.insertUser(person);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUser();
    }

    public Optional<User> getUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    public int deleteUser(UUID id) {
        return userDao.deleteUserById(id);
    }

    public int updateUser(UUID id, User newPerson) {
        return userDao.updateUserById(id, newPerson);
    }


}
