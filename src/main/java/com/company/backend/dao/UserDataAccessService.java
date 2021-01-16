package com.company.backend.dao;

import com.company.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//This is the class implements UserDao methods and queries the Users table.
//Users table definition can be found in resources/db.migration/V1__MainTables.sql file
@Repository("postgres-user")
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(User user) {
        final String sql = "INSERT INTO users (countryID, username, password, fName, lName, rid) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                user.getCountryID(),
                user.getUsername(),
                user.getPassword(),
                user.getfName(),
                user.getlName(),
                user.getRid()
        );
        return 0;
    }

    @Override
    public List<User> selectAllUser() {
        final String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (resultSet, i) ->  {
            //UUID id = UUID.fromString(resultSet.getString("id"));
            //Boolean is_admin = resultSet.getBoolean("is_admin");
            int countryID = resultSet.getInt("countryID");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String fName = resultSet.getString("fName");
            String lName = resultSet.getString("lName");
            int rid = resultSet.getInt("rid");
            return new User(countryID, username, password, fName, lName, rid);
        });
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        final String sql = "SELECT * FROM users WHERE username = ?";
        User user = jdbcTemplate.queryForObject(
                sql,
                new Object[]{username},
                (resultSet, i) ->  {
                    int countryID = resultSet.getInt("countryID");
                    String user_name = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String fName = resultSet.getString("fName");
                    String lName = resultSet.getString("lName");
                    int rid = resultSet.getInt("rid");
                    return new User(countryID, user_name, password, fName, lName, rid);
                });
        return Optional.ofNullable(user);
    }

    @Override
    public int deleteUserById(UUID id) {
        return 0;
    }

    @Override
    public int updateUserById(UUID id, User user) {
        return 0;
    }
}
