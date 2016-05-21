package com.BookStore.core.repositories;

import com.BookStore.core.models.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by victor.
 */
public interface ClientRepository extends BookStoreRepository<Client>, ClientRepositoryCustom {

    @Query("select c from Client c")
    @EntityGraph(value = "clientWithBooks", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithBooksSpring();
}
