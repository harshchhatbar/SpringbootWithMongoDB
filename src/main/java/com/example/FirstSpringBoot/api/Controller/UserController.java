package com.example.FirstSpringBoot.api.Controller;

import com.example.FirstSpringBoot.api.Model.UserMflix;
import com.example.FirstSpringBoot.api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    private List<UserMflix> getAllUsers() {
        List<UserMflix> allUser = userService.getAllUser();
        return allUser;
    }

    @PutMapping("/insert")
    private UserMflix InsertUser(@RequestBody UserMflix user) {
        return userService.insertUser(user);
    }

    @GetMapping("/find/{userName}")
    private List<UserMflix> findUser(@PathVariable("userName") String name) {
        List<UserMflix> userList = userService.findUser(name);
        return userList;
    }

}
