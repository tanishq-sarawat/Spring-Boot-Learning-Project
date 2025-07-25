package com.example.demo;

import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @CsvSource({
            "tanishq",
            "tanishq sarawat",
            "aditya"
    })
    public void testFindByUserName(String name) {
        assertNotNull(  userRepository.findByUserName(name));
    }
}
