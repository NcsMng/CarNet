package com.example.carnet.services;

import com.example.carnet.model.User;
import com.example.carnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.caseSensitive;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        User u = new User();
        u.setEmail(email);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("email", ignoreCase())
                .withIgnorePaths("user_id")
                .withIgnorePaths("is_enabled")
                .withIgnoreNullValues();
        Example<User> exampleUser = Example.of(u, exampleMatcher);
        Optional<User> maybeUser = userRepository.findOne(exampleUser);
        return maybeUser.orElse(null);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public boolean validateUser(String email, String password) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        ExampleMatcher validateMatcher = ExampleMatcher.matching()
                .withMatcher("email",ignoreCase())
                .withMatcher("password", caseSensitive())
                .withIgnorePaths("user_id")
                .withIgnoreNullValues();
        Example<User> userExample = Example.of(u,validateMatcher);
        return userRepository.exists(userExample);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}

