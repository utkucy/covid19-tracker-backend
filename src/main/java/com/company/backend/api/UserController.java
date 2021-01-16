package com.company.backend.api;


import com.company.backend.model.User;
import com.company.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

//UserController is the api which communicates with frontend and take parameters and transmit it to DB queries.
//If queries returns a respond, it will send these responds to related URLs.
@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @CrossOrigin(origins = "*")
    @PostMapping // POST REQUEST
    public void addUser(@Valid @NotNull @RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping // GET REQUEST
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{username}")
    public User getUserById(@PathVariable("username") String username) {
        return userService.getUserByUsername(username).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "Unable to find user"));
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody User userToUpdate) {
        userService.updateUser(id, userToUpdate);
    }


}























// 39path variable. orijinal path'e id'yi ekler ve ordan request atabilirsin. Ülke bazlı gösterimde işe yarayabilir.
// Eklenecek id'yi buradan alır.