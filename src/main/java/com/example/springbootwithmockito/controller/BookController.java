package com.example.springbootwithmockito.controller;

import java.util.List;
import java.util.Optional;

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
    private BookRepository bookRepository;


    @PostMapping(value = "save")
    public Object saveBook(@RequestBody Book book){
        return bookRepository.save(book);
    }


    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping(value = "{bookId}")
    public Optional<Book> getBookById(@PathVariable(value = "bookId") Long bookId){
        return bookRepository.findById(bookId);
    }


    @PutMapping
    public Book updateBookRecord(@RequestBody Book book) throws Exception {
        if(book == null || book.getId() == null){
            throw new Exception("Book not found in our records");
        }

        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if(!optionalBook.isPresent()){
            throw new Exception("This book is not present in our records, sorry");
        }
        Book existingBook = optionalBook.get();
        existingBook.setName(book.getName());
        existingBook.setSummary(book.getSummary());
        existingBook.setRating(book.getRating());

    return bookRepository.save(existingBook);
    }

    @DeleteMapping(value = "{bookId}")
    public void deleteBook(@PathVariable(value = "bookId") Long bookId){
        bookRepository.deleteById(bookId);
    }
}
