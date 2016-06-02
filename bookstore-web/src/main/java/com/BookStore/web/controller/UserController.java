package com.BookStore.web.controller;

import com.BookStore.core.models.User;
import com.BookStore.core.service.UserService;
import com.BookStore.web.converter.UserConverter;
import com.BookStore.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;


    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/vnd.api+json")
    public UserDto getUserDetails(Authentication authentication) {
        log.trace("getUserDetails: authentication={}", authentication);

        if (authentication == null) {
            log.warn("getUserDetails: user not available; probably not authenticated.");
            return new UserDto();
        }
        User user = userService.getUserByUserName(authentication.getName());
        UserDto userDto = userConverter.convertModelToDto(user);

        log.trace("getUserDetails: userDto={}", userDto);
        return userDto;
    }
}
