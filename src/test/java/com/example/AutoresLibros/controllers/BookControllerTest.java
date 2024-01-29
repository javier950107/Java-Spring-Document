package com.example.AutoresLibros.controllers;

import com.example.AutoresLibros.entities.Author;
import com.example.AutoresLibros.entities.Book;
import com.example.AutoresLibros.services.BookServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImp bookService;

    @Autowired
    private ObjectMapper objectMapper;


    @DisplayName("Test on insert a new book example then return that book inserted")
    @Test
    public void insertBookTest() throws Exception{
        Book bookInserted = Book.builder()
                .name("Holiwis")
                .author(new Author(1L, "John", "Doe", null))
                .build();

        when(bookService.insertBook(bookInserted)).thenReturn(bookInserted);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookInserted)))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Holiwis"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author.name").value("John"))
                .andDo(MockMvcResultHandlers.print())
                ;
    }
}
