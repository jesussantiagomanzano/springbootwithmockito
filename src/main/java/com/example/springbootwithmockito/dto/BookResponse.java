package com.example.springbootwithmockito.dto;

import com.example.springbootwithmockito.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

/**
 *     private List<BookDTO> content;
 *     private int pageNo;
 *     private int pageSize;
 *     private long totalElements;
 *     private int totalPages;
 *     private  boolean last;
 *
 */
public class BookResponse {
    private List<BookDTO> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private  boolean last;

}
