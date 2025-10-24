package com.bookmydoctor.bookMyDoctor.service;
import com.bookmydoctor.bookMyDoctor.entity.User;
import com.bookmydoctor.bookMyDoctor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        // todo: encrypt password later
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new RuntimeException("Invalid password");
            }
        }
        throw new RuntimeException("User not found");
    }
}