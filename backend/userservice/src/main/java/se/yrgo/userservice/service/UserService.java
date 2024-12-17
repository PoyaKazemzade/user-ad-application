package se.yrgo.userservice.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import se.yrgo.userservice.data.UserRepository;
import se.yrgo.userservice.domain.User;
import se.yrgo.userservice.domain.UserAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createUserData() {
        // List to hold example users
        List<User> users = new ArrayList<>();

        // Creating Users and Addresses
        var user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");

        var address1 = new UserAddress("Highway to Hell", "Heaven", "12345", null);
        user1.setAddress(address1);
        address1.setUser(user1);

        var user2 = new User();
        user2.setName("Jane Smith");
        user2.setEmail("jane.smith@example.com");

        var address2 = new UserAddress("Sunset Blvd", "Los Angeles", "90001", null);
        user2.setAddress(address2);
        address2.setUser(user2);

        // Add users to the list
        users.add(user1);
        users.add(user2);

        // Save only if the user doesn't already exist
        for (User user : users) {
            if (!userRepository.existsByEmail(user.getEmail())) {
                userRepository.save(user);
                System.out.println("Saved user: " + user.getName());
            } else {
                System.out.println("User already exists: " + user.getName());
            }
        }
    }

    public User saveUser(User user) {
        if(user.getAddress() !=null) {
            return userRepository.save(user);
        }
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
