package com.BookStore.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity<Integer> implements Serializable {
    @Column(name="title", nullable = false)
    private String title;

    @Column(name="isbn", nullable = false)
    private Long isbn;

    @Column(name="year", nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name="id")
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return title.equals(book.title) && isbn.equals(book.isbn) && year.equals(book.year);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + year.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn=" + isbn +
                ", year=" + year +
                '}';
    }
}