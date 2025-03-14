package sde.homework.demo.githubmanager.service;

import org.springframework.stereotype.Service;
import sde.homework.demo.githubmanager.model.User;
import sde.homework.demo.githubmanager.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String email, String password) {
        Optional<User> existing = userRepository.findByUsername(username);
        if(existing.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // In production, never store plain text passwords
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

