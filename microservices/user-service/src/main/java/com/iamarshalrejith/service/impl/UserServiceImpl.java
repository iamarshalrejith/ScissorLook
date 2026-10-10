package com.iamarshalrejith.service.impl;

import com.iamarshalrejith.service.UserService;

import org.springframework.stereotype.Service;

import com.iamarshalrejith.exception.UserException;
import com.iamarshalrejith.repository.UserRepository;
import java.util.Optional;
import com.iamarshalrejith.modal.User;


import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Override 
    public User getUserById(Long id) throws UserException{
        Optional<User> otp = userRepository.findById(id);
        if(otp.isPresent()){
            return otp.get();
        }
        throw new UserException("User not found");
    }

    @Override 
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) throws UserException{
        Optional<User> otp = userRepository.findById(id);
        if(otp.isEmpty()){
            throw new UserException("User not found with id"+id);
        }
        User existingUser = otp.get();

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setUsername(user.getUsername());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) throws UserException{
        Optional<User> otp = userRepository.findById(id);
        if(otp.isEmpty()){
            throw new UserException("User not exist with id"+id);
        }
        userRepository.deleteById(otp.get().getId());
    }
}

