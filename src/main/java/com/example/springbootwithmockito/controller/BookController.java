package com.example.springbootwithmockito.controller;

import java.util.List;
import java.util.Optional;

import com.example.springbootwithmockito.dto.BookResponse;
import com.example.springbootwithmockito.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springbootwithmockito.entity.Book;
import com.example.springbootwithmockito.repository.BookRepository;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private BookService bookService;


    @PostMapping(value = "save")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) throws Exception {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
       // return bookService.save(book);
    }


    @GetMapping("getAllBooksPage")
    public BookResponse getAllBooks(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        return bookService.findAll(pageNo, pageSize);
    }

    @GetMapping("getAllBooks")
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


    @GetMapping(value = "genre")
    public List<Book> findBooksByGenre(@PathVariable(value = "genre")String genre){
        return bookService.findByGenre(genre);
    }

    @DeleteMapping("/delete/{bookId}")
    public void deleteBook(@RequestParam("bookId") long bookId){
        bookService.deleteById(bookId);

    }




}
