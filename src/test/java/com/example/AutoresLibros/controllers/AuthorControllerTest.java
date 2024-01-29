package com.example.AutoresLibros.controllers;

import com.example.AutoresLibros.entities.Author;
import com.example.AutoresLibros.services.AuthorServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorServiceImp authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Test get all data in controllers get the list of authors")
    @Test
    public void getAllAuthors() throws Exception{
        Author newAuthor = new Author(1L, "John", "Doe", null);
        Author newAuthor2 = new Author(2L, "Jane", "Smith", null);

        when(authorService.getAuthors()).thenReturn(List.of(newAuthor, newAuthor2));

        mockMvc.perform(MockMvcRequestBuilders.get("/authors/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

        Mockito.verify(authorService, Mockito.times(1)).getAuthors();

    }

    @DisplayName("Test When insert a new data in the controller then return the new one")
    @Test
    public void insertNewAuthorTest() throws Exception {
        Author newAuthor = new Author(1L, "John", "Doe", null);

        when(authorService.createAuthor(newAuthor)).thenReturn(newAuthor);

        mockMvc.perform(MockMvcRequestBuilders.post("/authors/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newAuthor)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"));

        Mockito.verify(authorService, Mockito.times(1)).createAuthor(newAuthor);
    }

    @DisplayName("Test when i find a user by name then give me that user")
    @Test
    public void testFindUserByName() throws Exception{
        Author authorTest = new Author(1L, "John", "Doe", null);

        when(authorService.getAuthorByName("John")).thenReturn(authorTest);

        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{name}", "John")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));

        Mockito.verify(authorService, Mockito.times(1)).getAuthorByName("John");
    }

    @DisplayName("Test update author by id")
    @Test
    public void updateAuthorById() throws Exception{
        Author updated = new Author(1L, "Jane", "Smith", null);

        when(authorService.updateAuthorById(updated.getId(), updated)).thenReturn(updated);

        mockMvc.perform(MockMvcRequestBuilders.put("/authors/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jane"));
    }

    @DisplayName("Test update author only name by id Test")
    @Test
    public void updateAuthorNameById() throws Exception{
        Author updated = new Author(1L, "John", "Doe", null);

        when(authorService.updateOnlyNameById(updated.getId(), updated.getName())).thenReturn(updated);

        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/update/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"));
    }

    @DisplayName("Test on delete user by id controller url")
    @Test
    public void deleteAuthorByIdTest() throws Exception{
        doNothing().when(authorService).deleteAuthorById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
