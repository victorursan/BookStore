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
    private String title;
    private Integer year;
    private Integer author;
}
