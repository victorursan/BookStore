package com.BookStore.web.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by radu.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class BaseDto implements Serializable {
    private Integer id;
}
