package com.example.lab3waa.service;

import com.example.lab3waa.data.BookRepository;
import com.example.lab3waa.domain.Book;
import com.example.lab3waa.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    EmailSender emailSender;

    public void add(Book book) {
        bookRepository.save(book);
        emailSender.sendEmail(book.getIsbn(), "Welcome");
    }

    public void update(Book book) {
        bookRepository.save(book);
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public void delete(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        emailSender.sendEmail(book.getIsbn(), "Good Bye");
        bookRepository.delete(isbn);
    }

    public Collection<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }
}
