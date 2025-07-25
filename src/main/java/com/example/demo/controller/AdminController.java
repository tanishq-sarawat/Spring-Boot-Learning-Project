package com.example.demo.controller;

import com.example.demo.Entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("/all-users")
    public List<User> getEntries() {
        return userService.getEntries();
    }

    @PostMapping("/add-user")
    public void addUser(@RequestBody User user) {
        userService.saveEntryAdmin(user);
    }
}
