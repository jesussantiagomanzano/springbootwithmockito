package com.example.springbootwithmockito.entity;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.Optional;

@Entity
@Table(name = "book_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String summary;

    @NonNull
    private String genre;

    private int rating;
}
