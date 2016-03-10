package com.BookStore.Model.Validators;

@FunctionalInterface
public interface IValidator<T> {
    /** Checks if the suplied entity is valid
     * @param entity the entity to be checked
     * @throws ValidatorException if the entity is not valid
     */
    void validate(T entity) throws ValidatorException;
}

