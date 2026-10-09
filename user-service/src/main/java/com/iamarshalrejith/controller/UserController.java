package com.iamarshalrejith.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.iamarshalrejith.modal.User;
import com.iamarshalrejith.service.UserService;


@RestController
@RequiredArgsConstructor 
public class UserController{

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception{
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable Long id) throws Exception{
        User UpdateUser = userService.updateUser(id,user);
        return new ResponseEntity<>(UpdateUser, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) throws Exception{
       userService.deleteUser(id);
       return new ResponseEntity<>("User deleted",HttpStatus.ACCEPTED);
    }
}