package com.BookStore.web.repositories;

import com.BookStore.web.models.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by victor.
 */

@NoRepositoryBean
@Transactional
public interface BookStoreRepository<T extends BaseEntity<Integer>> extends JpaRepository<T, Integer> {
}
