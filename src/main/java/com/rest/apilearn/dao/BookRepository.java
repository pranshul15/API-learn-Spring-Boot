package com.rest.apilearn.dao;

import org.springframework.data.repository.CrudRepository;

import com.rest.apilearn.entities.Book;
import java.util.List;


public interface BookRepository extends CrudRepository<Book,Integer>{
    public List<Book> findById(int id);
}
