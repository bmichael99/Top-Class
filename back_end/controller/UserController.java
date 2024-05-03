package com.topclass.schedulesystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topclass.schedulesystem.model.Rating;
import com.topclass.schedulesystem.model.User;
import com.topclass.schedulesystem.model.userData;
import com.topclass.schedulesystem.service.UserService;
import com.topclass.schedulesystem.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.saveUser(user);
        return "New User is added";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public String addUserData(@RequestHeader(value = "Authorization") String token, @RequestBody List<userData> data) throws JsonProcessingException {
        //String userEmail = ExtractJWT.payloadJWTExtraction(token);

        String userEmail = token;
        User user = new User(userEmail,data);

        if(data != null){
            userService.deleteUser(user.getID());
            userService.saveUser(user);
        }

        return "successfully added user with email address " + userEmail;
    }

    @GetMapping("/getUser")
    public Optional<User> getUserData(@RequestHeader(value = "Authorization") String token) throws JsonProcessingException {
        //String userEmail = ExtractJWT.payloadJWTExtraction(token);
        String userEmail = token;
        return userService.getByID(userEmail);


    }

}
