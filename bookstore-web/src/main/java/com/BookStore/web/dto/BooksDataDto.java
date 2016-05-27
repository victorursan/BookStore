package com.BookStore.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by victor on 4/28/16.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooksDataDto implements Serializable {
    private Set<BookDto> books;

}
