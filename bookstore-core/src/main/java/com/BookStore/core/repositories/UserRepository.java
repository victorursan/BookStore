package com.BookStore.core.repositories;

import com.BookStore.core.models.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends BookStoreRepository<User> {

    @Query("select u from User u where u.userName=?1")
    User getUserByUserName(String userName);
}
