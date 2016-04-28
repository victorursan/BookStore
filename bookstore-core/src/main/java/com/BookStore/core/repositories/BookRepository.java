package com.BookStore.core.repositories;

import com.BookStore.core.models.Book;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by victor.
 */
@NoRepositoryBean
@Transactional
public interface BookRepository extends BookStoreRepository<Book> {
}
