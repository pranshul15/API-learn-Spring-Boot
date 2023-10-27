package com.rest.apilearn.services;

import java.util.List;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;

import com.rest.apilearn.dao.BookRepository;
import com.rest.apilearn.entities.Book;

// @Service
@Component
public class BookService {
    /* 
    private static List<Book> list = new ArrayList<>();
    
    static{
        list.add(new Book(1234,"Java Complete Reference","Author 1"));
        list.add(new Book(1235,"Java Head First","Author 2"));
        list.add(new Book(1236,"Java Go","Author 3"));
    }
    */
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return (List<Book>) this.bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return (Book) this.bookRepository.findById(id).get(0);
        // book = list.stream().filter(e->e.getId()==id).findFirst().get();
        // return book;
    }

    public Book addBook(Book b) {
        // list.add(b);
        // return b;
        return this.bookRepository.save(b);
    }

    public void deleteBook(int id) {
        // list = list.stream().filter(book->book.getId()!=id).collect(Collectors.toList());
        this.bookRepository.deleteById(id);
    }
    
    public void updateBook(Book book, int bookId) {
        /*        
        list = list.stream().map(b->{
            if(b.getId()==bookId) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());
        */
        book.setId(bookId);
        this.bookRepository.save(book);
    }
}
