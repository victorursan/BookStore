package com.BookStore.web.converter;

import com.BookStore.core.models.BaseEntity;
import com.BookStore.web.dto.BaseDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by victor on 5/19/16.
 */
public abstract class BaseConverter<Model extends BaseEntity<Integer>, Dto extends BaseDto> implements Converter<Model, Dto> {

    @Override
    public Model convertDtoToModel(Dto dto) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Dto convertModelToDto(Model model) {
        throw new RuntimeException("not implemented");
    }

    public Set<Integer> convertModelsToIDs(Set<Model> models) {
        return models.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> convertDTOsToIDs(Set<Dto> dtos) {
        return dtos.stream()
                .map(BaseDto::getId)
                .collect(Collectors.toSet());
    }

    public Set<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toSet());
    }
}
