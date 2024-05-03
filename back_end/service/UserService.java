package com.topclass.schedulesystem.service;

import com.topclass.schedulesystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();



    public String deleteUser(String ID);

    public Optional<User> getByID(String ID);
}
