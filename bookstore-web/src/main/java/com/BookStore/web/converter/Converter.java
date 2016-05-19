package com.BookStore.web.converter;


import com.BookStore.core.models.BaseEntity;
import com.BookStore.web.dto.BaseDto;

public interface Converter<Model extends BaseEntity<Integer>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

