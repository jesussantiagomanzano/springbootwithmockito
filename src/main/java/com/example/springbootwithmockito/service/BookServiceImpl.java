package com.example.springbootwithmockito.service;

import com.example.springbootwithmockito.entity.Book;
import com.example.springbootwithmockito.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;



    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return Optional.empty();
    }

    @Override
    public Book save(Book book) throws Exception{
        if(book == null ){
            throw new Exception("Book not found in our records");
        }
        if(book.getId() == null){
            return bookRepository.save(book);
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

    @Override
    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
