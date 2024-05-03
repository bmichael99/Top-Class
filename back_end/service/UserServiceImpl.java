package com.topclass.schedulesystem.service;

import com.topclass.schedulesystem.model.User;
import com.topclass.schedulesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(String ID) {
        userRepository.deleteById(ID);
        return "All entries in rating table deleted";
    }

    @Override
    public Optional<User> getByID(String ID) {
        return userRepository.findById(ID);
    }
}
