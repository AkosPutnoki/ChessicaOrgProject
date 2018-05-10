package com.chessica.service;

import com.chessica.domain.userRelated.User;
import com.chessica.exception.FailedDataVerificationException;
import com.chessica.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registration(String username, String password, String email){
        if (userRepository.getUserByEmail(email) != null || userRepository.getUserByName(username) != null)
            throw new FailedDataVerificationException("Registration failed due to existing username/email!");

        User newUser = new User(username, email, BCrypt.hashpw(password, BCrypt.gensalt()));

        userRepository.save(newUser);
        return newUser;
    }


    public User login(String username, String password){
        User currentUser = userRepository.getUserByName(username);
        if(currentUser != null && BCrypt.checkpw(password, currentUser.getPassword())){
            return currentUser;
        }
        throw new FailedDataVerificationException("Login failed");

    }
}
