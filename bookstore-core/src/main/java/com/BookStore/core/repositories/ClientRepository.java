package com.BookStore.core.repositories;

import com.BookStore.core.models.Client;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by victor.
 */
@NoRepositoryBean
@Transactional
public interface ClientRepository extends BookStoreRepository<Client> {

}
