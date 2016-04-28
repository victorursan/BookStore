package com.BookStore.web.Repository;

import com.BookStore.web.Model.Validators.ValidatorException;

import java.util.Optional;


public interface IRepository<T> {
    /**
     * Adds a new element
     *
     * @param elem element to be saved
     * @throws ValidatorException if the element si not valid
     */
    void add(T elem) throws ValidatorException;

    /**
     * Finds the element with the given {@code id}
     *
     * @param id the id of the element in the repository
     * @return an {@code Optional} - null if the element doesn't exist, otherwise the element
     */
    Optional<T> get(int id);

    /**
     * Returns all the elements in the Repository
     *
     * @return all the elements
     */
    Iterable<T> getAll();

    /**
     * @param elem the new element in the updated repository repository
     * @return an {@code Optional} - the entity if it wasn't updated, otherwise null
     * @throws ValidatorException - if the element si not valid
     */
    Optional<T> update(T elem) throws ValidatorException;

    /**
     * Remove an element in the repository with an id
     *
     * @param id the id of the element in the repository
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     */
    Optional<T> delete(int id);
}