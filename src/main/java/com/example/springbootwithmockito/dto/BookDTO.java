package com.example.springbootwithmockito.dto;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {


    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String summary;

    @NonNull
    private String genre;

    private int rating;
}
