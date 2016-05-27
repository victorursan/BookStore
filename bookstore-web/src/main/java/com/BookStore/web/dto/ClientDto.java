package com.BookStore.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * Created by victor on 5/12/16.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClientDto extends BaseDto{
    private String firstName;
    private String lastName;
    private Set<Integer> books;

}
