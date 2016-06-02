package com.BookStore.core.service;


import com.BookStore.core.models.User;

public interface UserService {

    User getUserByUserName(String userName);
}
