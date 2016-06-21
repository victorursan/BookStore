package com.BookStore.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * Created by victor on 6/21/16.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthorDto extends BaseDto {
    private String name;
    private Set<Integer> books;
}
