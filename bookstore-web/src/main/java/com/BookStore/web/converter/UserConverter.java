package com.BookStore.web.converter;

import com.BookStore.core.models.User;
import com.BookStore.web.dto.UserDto;
import org.springframework.stereotype.Component;


@Component
public class UserConverter extends BaseConverter<User, UserDto> {

    @Override
    public UserDto convertModelToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setRole(user.getUserRole().name());

        return userDto;
    }
}
