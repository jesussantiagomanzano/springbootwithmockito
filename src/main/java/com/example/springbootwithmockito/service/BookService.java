package com.example.springbootwithmockito.service;


import com.example.springbootwithmockito.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {



    List<Book> findAll();

    Optional<Book> findById(Long bookId);

    Book save(Book book) throws Exception;

    void deleteById(Long bookId);
}
