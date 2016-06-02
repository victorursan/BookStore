package com.BookStore.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto extends BaseDto{
    private String userName;
    private String role;

    public UserDto(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }
}
