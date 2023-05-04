package com.example.springbootwithmockito.controller;

import com.example.springbootwithmockito.dto.BookDTO;
import com.example.springbootwithmockito.dto.BookResponse;
import com.example.springbootwithmockito.entity.Book;
import com.example.springbootwithmockito.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book;

    private String urlTemplate;

    @BeforeEach
    public void method(){
         book = new Book(1L, "Guardians of the galaxy VOL III",
                "No summaries yet","Fiction", 3);
         urlTemplate = "/api/v1/";
    }

    @Test
    public void bookCreated() throws Exception{
        BDDMockito.given(bookService.save(ArgumentMatchers.any()))
                .willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(book)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(book.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre", CoreMatchers.is(book.getGenre())))
        .andDo(MockMvcResultHandlers.print())
        ;

    }

    @Test
    public void getAllBooks() throws Exception {

        BookResponse bookResponse = new BookResponse(
                Stream.of(new BookDTO(11l, "", "", "", 3)).collect(Collectors.toList()),
                1,1,1, 1L, true
        );
        Mockito.when(bookService.findAll(1,10)).thenReturn(bookResponse);

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.get(urlTemplate + "getAllBooksPage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("pageNo", "1")
                        .param("pageSize", "10")
        );


        response.andExpect(MockMvcResultMatchers.status().isOk())
               // .andDo(MockMvcResultHandlers.print());
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(bookResponse.getContent().size())))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void deleteBookById() throws Exception {
        Mockito.doNothing().when(bookService).deleteById(1L);

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.delete(urlTemplate + "delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("bookId", "1"));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }



}
