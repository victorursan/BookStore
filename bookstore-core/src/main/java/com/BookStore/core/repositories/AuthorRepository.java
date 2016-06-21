package com.BookStore.core.repositories;

import com.BookStore.core.models.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by victor on 6/21/16.
 */

public interface AuthorRepository extends BookStoreRepository<Author> {

    @SuppressWarnings("JpaQlInspection")
    @Query("select a from Author a")
    @EntityGraph(value = "authorWithBooks", type = EntityGraph.EntityGraphType.LOAD)
    List<Author> findAllWithBooksSpring();
}
