package com.example.demo.service;

import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTests {

   /* @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEntry() {
        // Create a test user
        User user = User.builder()
                .userName("testuser")
                .Password("password")
                .build();

        // Mock the save method
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Test the saveEntry method
        userService.saveEntry(user);

        // Verify that userRepository.save was called once
        verify(userRepository, times(1)).save(any(User.class));

        // Verify that the user has the USER role
        assertTrue(user.getRoles().contains("USER"));

        // Verify that the password was encoded
        assertNotEquals("password", user.getPassword());
    }

    @Test
    public void testSaveEntryAdmin() {
        // Create a test user
        User user = User.builder()
                .userName("adminuser")
                .Password("password")
                .build();

        // Mock the save method
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Test the saveEntryAdmin method
        userService.saveEntryAdmin(user);

        // Verify that userRepository.save was called once
        verify(userRepository, times(1)).save(any(User.class));

        // Verify that the user has both USER and ADMIN roles
        assertTrue(user.getRoles().contains("USER"));
        assertTrue(user.getRoles().contains("ADMIN"));
    }

    @Test
    public void testSaveEntryOld() {
        // Create a test user
        User user = User.builder()
                .userName("olduser")
                .Password("password")
                .build();

        // Mock the save method
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Test the saveEntryold method
        userService.saveEntryold(user);

        // Verify that userRepository.save was called once
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetEntries() {
        // Create test users
        User user1 = User.builder().userName("user1").Password("pass1").build();
        User user2 = User.builder().userName("user2").Password("pass2").build();
        List<User> users = Arrays.asList(user1, user2);

        // Mock the findAll method
        when(userRepository.findAll()).thenReturn(users);

        // Test the getEntries method
        List<User> result = userService.getEntries();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUserName());
        assertEquals("user2", result.get(1).getUserName());
    }

    @Test
    public void testGetEntryById() {
        // Create a test user
        User user = User.builder()
                .id("123")
                .userName("testuser")
                .Password("password")
                .build();

        // Mock the findById method
        when(userRepository.findById("123")).thenReturn(Optional.of(user));

        // Test the getEntryById method
        Optional<User> result = userService.getEntryById("123");

        // Verify the result
        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUserName());
    }

    @Test
    public void testDeleteEntry_Success() {
        // Mock the deleteByUserName method to not throw an exception
        doNothing().when(userRepository).deleteByUserName("testuser");

        // Test the deleteEntry method
        boolean result = userService.deleteEntry("testuser");

        // Verify the result
        assertTrue(result);
        verify(userRepository, times(1)).deleteByUserName("testuser");
    }

    @Test
    public void testDeleteEntry_Failure() {
        // Mock the deleteByUserName method to throw an exception
        doThrow(new RuntimeException("Delete failed")).when(userRepository).deleteByUserName("nonexistent");

        // Test the deleteEntry method
        boolean result = userService.deleteEntry("nonexistent");

        // Verify the result
        assertFalse(result);
        verify(userRepository, times(1)).deleteByUserName("nonexistent");
    }

    @Test
    public void testFindByUserName() {
        // Create a test user
        User user = User.builder()
                .userName("testuser")
                .Password("password")
                .build();

        // Mock the findByUserName method
        when(userRepository.findByUserName("testuser")).thenReturn(user);

        // Test the findByUserName method
        User result = userService.findByUserName("testuser");

        // Verify the result
        assertNotNull(result);
        assertEquals("testuser", result.getUserName());
    }*/
}
