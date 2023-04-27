package com.example.springbootwithmockito.controller;

import java.util.List;
import java.util.Optional;

import com.example.springbootwithmockito.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootwithmockito.entity.Book;
import com.example.springbootwithmockito.repository.BookRepository;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private BookService bookService;


    @PostMapping(value = "save")
    public Book saveBook(@RequestBody Book book) throws Exception {
        return bookService.save(book);
    }


    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    @GetMapping(value = "{bookId}")
    public Optional<Book> getBookById(@PathVariable(value = "bookId") Long bookId){
        return bookService.findById(bookId);
    }


    @PutMapping
    public Book updateBookRecord(@RequestBody Book book) throws Exception {


    return bookService.save(book);
    }

    @DeleteMapping(value = "{bookId}")
    public void deleteBook(@PathVariable(value = "bookId") Long bookId){
        bookService.deleteById(bookId);
    }
}
