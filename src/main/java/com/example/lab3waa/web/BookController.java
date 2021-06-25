package com.example.lab3waa.web;

import com.example.lab3waa.domain.Book;
import com.example.lab3waa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    List<Book> books = new ArrayList<Book>();

    public BookController() {
        books.add(new Book("111", "sebitas", "deport", 12.2));
        // books.add(new Book("222", "alejandrita", "rock", 620d));
    }

    @PostMapping("/book")
    public ResponseEntity<?> addBook(@RequestBody @Valid Book book) {
        bookService.add(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PutMapping("/book/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody @Valid Book book) {
       bookService.update(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);


        if (book == null) {
            return new ResponseEntity<CustomError>(new CustomError("Book # "
                    + isbn + " is not available"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/book/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            return new ResponseEntity<CustomError>(new CustomError("Book # "
                    + isbn + " is not available"), HttpStatus.NOT_FOUND);
        }

        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/book")
//    public ResponseEntity<?> getAllBooks() {
//        Books wrapper = new Books(books);
//        return new ResponseEntity<Books>(wrapper, HttpStatus.OK);
//    }

    @GetMapping("/book")
    public ResponseEntity<?> searchBooks(@RequestParam(value = "author", required = false) String author) {
        Books wrapper = new Books();

        if (author == null) {
            wrapper.setBooks(bookService.findAll());
        } else {
            wrapper.setBooks(bookService.findByAuthor(author));

            if (wrapper.getBooks() == null) {
                return new ResponseEntity<CustomError>(new CustomError("Author # "
                        + author + " is not available"), HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<Books>(wrapper, HttpStatus.OK);

    }


}
