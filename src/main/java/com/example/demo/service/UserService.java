package com.example.demo.service;

import com.example.demo.Entity.Entry;
import com.example.demo.Entity.User;
import com.example.demo.repository.EntryRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component

public class UserService {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));

        userRepository.save(user);
    }
    public void saveEntryAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));

        userRepository.save(user);
    }


    public void saveEntryold(User user){
        userRepository.save(user);
    }



    public List<User> getEntries(){
        return userRepository.findAll();
    }

    public Optional<User> getEntryById(String id){
        return userRepository.findById(id);
    }
    public boolean deleteEntry(String username){

        try {
            userRepository.deleteByUserName(username);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

}
