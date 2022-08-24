package com.example.springbootusersappapi.controllers;

import com.example.springbootusersappapi.models.User;
import com.example.springbootusersappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(this.userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable(value = "username") String username) {
        return new ResponseEntity<User>(this.userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping(value = "/params")
    public ResponseEntity<List<User>> getUserStartWithNickName(@RequestParam(value = "startWith", required = false) String startWith) {
        return new ResponseEntity<List<User>>(this.userService.getUserStartWithNickName(startWith), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(this.userService.createUser(user), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(value = "username") String username) {
        return new ResponseEntity<User>(this.userService.updateUser(user, username), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "username") String username) {
        Boolean isDeleted = this.userService.deleteUser(username);
        System.out.println("isDeleted = " + isDeleted);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
