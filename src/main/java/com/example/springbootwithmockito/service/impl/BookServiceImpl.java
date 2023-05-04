package com.example.springbootwithmockito.service.impl;

import com.example.springbootwithmockito.dto.BookDTO;
import com.example.springbootwithmockito.dto.BookResponse;
import com.example.springbootwithmockito.entity.Book;
import com.example.springbootwithmockito.repository.BookRepository;
import com.example.springbootwithmockito.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }



    @Override
    public BookResponse findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Book> books = bookRepository.findAll(pageable);
        List<Book> listOfPokemon = books.getContent();
        List<BookDTO> content = listOfPokemon.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(content);
        bookResponse.setPageNo(books.getNumber());
        bookResponse.setPageSize(books.getSize());
        bookResponse.setTotalElements(books.getTotalElements());
        bookResponse.setTotalPages(books.getTotalPages());
        bookResponse.setLast(books.isLast());

        return bookResponse;
    }


    private BookDTO mapToDto(Book pokemon) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(pokemon.getId());
        bookDTO.setName(pokemon.getName());
        bookDTO.setGenre(pokemon.getGenre());
        return bookDTO;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
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
