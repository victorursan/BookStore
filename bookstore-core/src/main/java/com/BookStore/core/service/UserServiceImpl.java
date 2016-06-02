package com.BookStore.core.service;

import com.BookStore.core.models.User;
import com.BookStore.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }
}
