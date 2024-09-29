package com.jsp.PerformedCrudOperations.Service;

// import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.stereotype.Service;

import com.jsp.PerformedCrudOperations.Entity.User;
import com.jsp.PerformedCrudOperations.Repository.UserRepo;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    // create user
    public User createUser(User user) {

        Optional<User> user2 = userRepo.findByEmail(user.getEmail());
        if (user2.isPresent()) {
            return null;
        }
        return userRepo.save(user);
    }

    // get all users
    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findUserById(int id) {
        return userRepo.findById(id);
    }

    // Update user
    public User updateUser(int id, User user) {

        User user2 = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setAge(user.getAge());
        return userRepo.save(user2);
    }

    // delete user
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}
