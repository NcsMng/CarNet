package com.example.carnet.controller;

import com.example.carnet.model.User;
import com.example.carnet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/carnet/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/getSignedUser")
    public Principal user(Principal user) {
        return user;
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{email}")
    @PreAuthorize("hasAuthority('user:read')")
    public User getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/validate")
    @PreAuthorize("hasAuthority('user:read')")
    public boolean validateUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.validateUser(email, password);
    }
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('user:write')")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }


}
