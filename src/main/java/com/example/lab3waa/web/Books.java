package com.example.lab3waa.web;

import com.example.lab3waa.domain.Book;

import java.util.Collection;
import java.util.List;

public class Books {
    private Collection<Book> books;

    public Books(Collection<Book> books) {
        this.books = books;
    }

    public Books() {
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}
