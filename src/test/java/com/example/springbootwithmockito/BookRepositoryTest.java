package com.example.springbootwithmockito;


import com.example.springbootwithmockito.entity.Book;
import com.example.springbootwithmockito.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void saveAllReturnSavedBooks(){
        Book book = new Book(12L,"A new book","No summary for this book", "Novels",2);
        Book book1 = new Book(3L, "Another book", "No summary for this book", "Novels", 3);

        Book savedBook = bookRepository.save(book);

        Assertions.assertThat(savedBook).isNotNull();

        Assertions.assertThat(savedBook.getId()).isGreaterThan(0);
    }

    @Test
    public void getAllBooks(){
        Book book1 = new Book(3L,"Another book", "No summary for this book",  "", 3);
        Book book2 = new Book(12L,"A new book","No summary for this book","Novels",2);

        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> bookList = bookRepository.findAll();
        Assertions.assertThat(bookList).isNotNull();
        Assertions.assertThat(bookList.size()).isEqualTo(2);

    }

    @Test
    public void getAllBooksByGenre(){
        Book book1 = Book.builder().id(3L).name("Another book").summary("No summary for this book").rating(3).genre("Novels").build();
        Book book2= new Book(12L,"A new book","No summary for this book","Novels",2);

        bookRepository.save(book1);
        bookRepository.save(book2);

        String genre = "Novels";
        List<Book> bookList = bookRepository.findByGenre(genre);
        Assertions.assertThat(bookList).isNotNull();
        Assertions.assertThat(bookList.size()).isEqualTo(2);
        System.out.println("Book list " + bookList);

    }


    Optional<Book> getNewBook(){
        return Optional.of(new Book());
    }

    @Test
    public void updateBookRepository(){
        Book book = Book.builder().id(3L)
                .name("Another book").summary("No summary for this book")
                .rating(3).genre("Novels").build();

        bookRepository.save(book);

        Optional<Book> copy = bookRepository.findAll().stream().findFirst();

        String genre = "Fiction";
        String anotherTitle = "Another title";

        copy.get().setGenre(genre);
        copy.get().setName(anotherTitle);

        Book bookUpdated = bookRepository.save(copy.get());

        Predicate<String> myPredicate = string -> string.equals(anotherTitle);

        System.out.println("Book found: " + bookUpdated);
        Assertions.assertThat(myPredicate);

    }


    @Test
    public void lookBookRepository(){
        Book book = Book.builder().id(3L)
                .name("Another book").summary("No summary for this book")
                .rating(3).genre("Novels").build();

        bookRepository.save(book);

        Optional<Book> copy = bookRepository.findAll().stream().findFirst();

        String genre = "Fiction";
        String anotherTitle = "Another title";

        copy.get().setGenre(genre);
        copy.get().setName(anotherTitle);

        Book bookUpdated = bookRepository.save(copy.get());

        Predicate<String> myPredicate = string -> string.equals(anotherTitle);

        System.out.println("Book found: " + bookUpdated);
        Assertions.assertThat(myPredicate);

    }

}
