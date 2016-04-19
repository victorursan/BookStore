package com.BookStore.Repository.DbRepository;

import com.BookStore.Models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book1 = new Book();
        book1.setId(rs.getInt("bookid"));
        book1.setTitle(rs.getString("title"));
        book1.setAuthor(rs.getString("author"));
        book1.setISBN(rs.getLong("isbn"));
        book1.setGenre(rs.getString("genre"));
        book1.setPublisher(rs.getString("publisher"));
        book1.setAvailable(rs.getBoolean("available"));
        book1.setPrice(rs.getInt("price"));
        return book1;
    }
}
