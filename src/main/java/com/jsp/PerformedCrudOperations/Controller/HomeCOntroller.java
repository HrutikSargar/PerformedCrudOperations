package com.jsp.PerformedCrudOperations.Controller;

// import org.hibernate.mapping.List;
// import org.hibernate.mapping.List;
// import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.PerformedCrudOperations.Entity.User;
import com.jsp.PerformedCrudOperations.Service.UserService;

import lombok.Delegate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/home")
public class HomeCOntroller {

    @Autowired
    private UserService userService;

    // @GetMapping("/text")
    // public String home() {
    // return "this is message from controller";
    // }

    // CREATE
    @PostMapping("/save")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        User user2 = userService.createUser(user);
        if (user2 == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("user already exists with this email id: " + user.getEmail());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("user created" + user2);
    }

    // READ
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> data = userService.findAll();
        if (data.isEmpty()) {
            // Return 204 No Content if no users found
            return ResponseEntity.noContent().build();
        }
        // return 200 ok with data
        return ResponseEntity.ok(data);
    }

    // READ SPECIFIC USER BY ID
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<String> findUserById(@PathVariable int id) {
        User isFound = userService.findUserById(id).orElse(null);
        if (isFound == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(isFound.toString());
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {

        try {
            User updateUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updateUser);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        // return null;
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {

        Optional<User> isFound = userService.findUserById(id);
        if (isFound.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user does not exists");
    }

}
