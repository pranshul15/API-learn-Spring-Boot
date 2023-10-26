package com.rest.apilearn.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.apilearn.entities.Book;
import com.rest.apilearn.services.BookService;

@RestController
public class BookController {
    
    // @GetMapping("/book")
    // public Book getBook() {
        
    //     return new Book(4893,"Java Entity","Ross Gowlman");
    // }
    @Autowired
    private BookService bookService;

    /*
    @GetMapping("/book")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
    */

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = this.bookService.getAllBooks();
        if(list.size()<=0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    /*
    @GetMapping("/book/{id}")
    public Book getBookid(@PathVariable("id") int id) {
        return bookService.getBookById(id);
    }
    */
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookid(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    /*
    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        Book b = this.bookService.addBook(book);
        System.out.println(b);
        return b;
    }
    */
    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;
        try {
            b = this.bookService.addBook(book);
            System.out.println(b);
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
    @DeleteMapping("/book/{bookId}")
    public void deleteBook(@PathVariable("bookId") int book) {
        this.bookService.deleteBook(book);
    }
    */
    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int book) {
        try {
            this.bookService.deleteBook(book);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
    @PutMapping("/book/{bookId}")
    public Book updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
        this.bookService.updateBook(book, bookId);
        return book;
    }
    */
    @PutMapping("/book/{bookId}")
    public ResponseEntity<Book> updateBok(@RequestBody Book book, @PathVariable("bookId") int bookId) {
        try {
            this.bookService.updateBook(book, bookId);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}