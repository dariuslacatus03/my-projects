package com.exam.exam.service.implementations;

import com.exam.exam.model.Property;
import com.exam.exam.model.User;
import com.exam.exam.repository.PropertyRepository;
import com.exam.exam.repository.UserRepository;
import com.exam.exam.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        if (this.userRepository.findById(id).isEmpty())
        {
            return null;
        }
        return this.userRepository.findById(id).get();
    }

    @Override
    public User addUser(User userToAdd) {
        if (userRepository.findByName(userToAdd.getName()) == null)
        {
            this.userRepository.save(userToAdd);
            return userToAdd;
        }
        else
        {
            return null;
        }
    }

    @Override
    public User checkNameExistence(String name)
    {
        for (User user : userRepository.findAll())
        {
            if (name.equals(user.getName())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean verifySecretAnswer(String name, String answer) {
        User user = userRepository.findByName(name);
        return user.getSecretAnswer().equals(answer);
    }

    @Override
    public Property addPropertyToUser(String username, Property propertyToAdd) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByName(username));
        if (userOptional.isEmpty())
        {
            throw new EntityNotFoundException("User not found");
        }
        else
        {
            User userEntity = userOptional.get();
            propertyToAdd.getUsers().add(userEntity);
            userEntity.getProperties().add(propertyToAdd);
            userRepository.save(userEntity);
            propertyRepository.save(propertyToAdd);
        }
        return propertyToAdd;
    }
}
