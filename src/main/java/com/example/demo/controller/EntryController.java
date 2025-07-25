package com.example.demo.controller;

import com.example.demo.Entity.Entry;
import com.example.demo.Entity.User;
import com.example.demo.service.EntryService;
import com.example.demo.service.UserService;
import com.example.demo.repository.UserRepository;

import org.bson.types.ObjectId;
import com.example.demo.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;



@RestController
@RequestMapping("/Entries")
public class EntryController {

    @Autowired
    private EntryService entryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Entry> getEntries() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUserName(username);
        return user.getEntries();
    }
    @Transactional
    @PostMapping()
    public ResponseEntity<Object> addEntry(@RequestBody Entry entry){
        try {
            entry.setDate(LocalDateTime.now());
            entryService.saveEntry(entry);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.findByUserName(username);
            if (user != null) {

                user.getEntries().add(entry);
            }

            userService.saveEntryold(user);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{del}")
    public ResponseEntity<?> deleteEntry(@PathVariable String  del) {
        try {
            Optional<Entry> a = Optional.ofNullable(entryService.getEntryById(del).orElse(null));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.findByUserName(username);
            if(user.getEntries().contains(a.get())){
                entryService.deleteEntry(del);
            }
            user.getEntries().removeIf(e -> e.getId().equals(del));
            userService.saveEntryold(user);

            return new ResponseEntity<>(a, HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>("delete api not working or entry doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{put1}")
    public ResponseEntity<?> updateEntry(@PathVariable String put1, @RequestBody Entry entry) {
        Entry entry1 = entryService.getEntryById(put1).orElse(null);
        if (entry1 != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.findByUserName(username);
            if(!user.getEntries().contains(entry1)){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            entry1.setTitle(entry.getTitle() != ""  ? entry.getTitle() : entry1.getTitle()) ;
            entry1.setContent(entry.getContent());
            entryService.saveEntry(entry1);
            return ResponseEntity.ok(entry1);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
