package com.BookStore.core.repositories;

import com.BookStore.core.models.Client;

import java.util.List;

/**
 * Created by victor on 5/20/16.
 */
public interface ClientRepositoryCustom   {
    List<Client> findAllWithBooksSqlQuery();

    List<Client> findAllWithBooksJpql();

    List<Client> findAllWithBooksJpaCriteria();
}
