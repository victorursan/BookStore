package com.BookStore.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by victor on 5/13/16.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookDto extends BaseDto {
    private Long isbn;
    private String genre;
    private String publisher;
    private Integer price;
    private String author;
    private String title;
    private Boolean available;

}
