package com.example.lab3waa.data;

import com.example.lab3waa.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    private Map<String, Book> books = new HashMap<String, Book>();

    public void save(Book book) {
        books.put(book.getIsbn(), book);
    }

    public Book findByIsbn(String isbn) {
        return books.get(isbn);
    }

    public Collection<Book> findByAuthor(String author) {
        return books.values().stream().filter(x -> x.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public void delete(String isbn) {
        books.remove(isbn);
    }

    public Collection<Book> findAll() {
        return books.values();
    }
}