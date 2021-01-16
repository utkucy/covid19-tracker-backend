package com.company.backend.dao;

import com.company.backend.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


//This is UserDataAccessObject which is an interface and defines all user methods.
public interface UserDao {

    int insertUser(User user);

    /*default int insertUser(User user) {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }*/

    List<User> selectAllUser();

    Optional<User> selectUserByUsername(String username);

    int deleteUserById(UUID id);

    int updateUserById(UUID id, User user);

}
