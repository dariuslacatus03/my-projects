package com.exam.exam.service;

import com.exam.exam.model.Property;
import com.exam.exam.model.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User addUser(User userToAdd);
    User checkNameExistence(String name);
    boolean verifySecretAnswer(String name, String answer);

    Property addPropertyToUser(String username, Property propertyToAdd);
}
