package com.example.demo.controller;

import com.example.demo.Entity.User;
import com.example.demo.api.response.WeatherResponse;
import com.example.demo.service.EntryService;
import com.example.demo.service.UserService;
import com.example.demo.service.WeatherService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    WeatherService weatherService;


    @GetMapping("/{username}")
    public  User getUser(@PathVariable String username) {
        User user = userService.findByUserName(username);
        if (user != null) {
            new ResponseEntity<>(user, HttpStatus.FOUND);
            return user;
        }
        new ResponseEntity<>("nahi ha merpe", HttpStatus.BAD_REQUEST);
        return null;
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteuser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            User a1 = getUser(username);
            if (a1 == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.deleteEntry(username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateuser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User a1 = getUser(username);
        a1.setUserName(user.getUserName());
        a1.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveEntryold(a1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/weather")
    public ResponseEntity<?> gretting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String gretting = "";
        if (weatherResponse != null) {
                gretting = "weather feels like :" + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi :" + username + "\n" +gretting, HttpStatus.OK);
    }

}
