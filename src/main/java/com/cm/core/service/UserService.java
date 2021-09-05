package com.cm.core.service;

import com.cm.core.entity.User;
import com.cm.core.repository.UserRepository;
import com.cm.core.request_object.LoginRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        validateUser(user);

        return userRepository.insert(user);
    }

    public Optional<User> login(LoginRequest req) {
        // validate password...

        return userRepository.findByEmail(req.getEmail());
    }

    public User updateUser(ObjectId userId, User updatingUser) {
        validateUserExisting(userId);

        updatingUser.setId(userId);

        return userRepository.save(updatingUser);
    }

    public void deleteUser(ObjectId userId) {
        Optional<User> existingUser = userRepository.findById(userId);

        if (!existingUser.isPresent())
            throw new IllegalStateException("User Id: " + userId.toHexString() + " not found.");
        else userRepository.delete(existingUser.get());
    }

    public void validateUser(User user) {
        Optional<User> existingUserOpt = userRepository.findByEmail(user.getEmail());
        if (existingUserOpt.isPresent())
            throw new IllegalStateException("Email " + user.getEmail() + " has taken!");
    }

    public void validateUserExisting(ObjectId userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (!existingUser.isPresent())
            throw new IllegalStateException("User Id: " + userId.toHexString() + " not found.");
    }
}
