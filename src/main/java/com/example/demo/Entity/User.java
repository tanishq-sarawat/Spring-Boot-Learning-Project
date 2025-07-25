package com.example.demo.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
@Data



public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull

    private String userName;

    @NonNull
    private String Password;
    @NonNull
    private String email;
    private boolean sentiment;



    @DBRef
    private List<Entry> entries = new ArrayList<>();

    private List<String> roles = new ArrayList<>();

    public @NonNull String getPassword() {
        return Password;
    }

    public void setPassword(@NonNull String password) {
        Password = password;
    }
}
