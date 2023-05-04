package com.example.springbootwithmockito.repository;

import com.example.springbootwithmockito.dto.BookResponse;
import com.example.springbootwithmockito.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenre(String genre);

    BookResponse getAllBooks(int pageNo, int pageSize);

}
