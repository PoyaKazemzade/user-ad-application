package se.yrgo.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import se.yrgo.userservice.data.UserRepository;
import se.yrgo.userservice.domain.User;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
