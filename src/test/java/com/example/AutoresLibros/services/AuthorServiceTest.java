package com.example.AutoresLibros.services;

import com.example.AutoresLibros.entities.Author;
import com.example.AutoresLibros.repositories.AuthorRepository;
import org.apache.el.parser.AstSetData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImp authorService;

    public Author authorTest;

    @BeforeEach
    public void setUp(){
        authorTest = new Author(1L, "John", "Doe@", null);
    }

    @DisplayName("Test on insert data")
    @Test
    public void createAuthorTest(){
        when(authorRepository.save(authorTest)).thenReturn(authorTest);
        Author saved = authorService.createAuthor(authorTest);
        Assertions.assertEquals(saved.getName(), "John");
    }

    @DisplayName("Test insert when lastname doesnt contain and @ then return a exception")
    @Test
    public void createAuthorWithoutLastNameWrongTest(){
        Author author1 = new Author(1L, "Pepe", "Last", null);

        Throwable test = Assertions.assertThrows(RuntimeException.class, () -> {
            Author saved = authorService.createAuthor(author1);
        });

        Assertions.assertEquals(test.getMessage(), "The lastname must be contain @");

        verify(authorRepository, never()).save(any());
    }

    @DisplayName("Test get authors then give me all authors")
    @Test
    public void getAuthors(){
        Author author1 = new Author(2L, "Jack", "Sparrow", null);

        List<Author> test = Arrays.asList(author1, authorTest);

        when(authorRepository.findAll()).thenReturn(test);
        List<Author> found = authorService.getAuthors();
        Assertions.assertEquals(found.size(), 2);
    }

    @DisplayName("Get author by name test find by name and give that author")
    @Test
    public void getAuthorByNameTest(){
        when(authorRepository.findByName("John")).thenReturn(authorTest);
        Author found = authorService.getAuthorByName("John");

        Assertions.assertEquals(found.getName(), "John");
        Assertions.assertEquals(found.getLastName(), "Doe");
        Assertions.assertEquals(found.getId(), 1);
    }

    @DisplayName("Get author by name if not found the author return null")
    @Test
    public void testAuthorByNameNullTest(){
        when(authorRepository.findByName("Vi")).thenReturn(null);

        Author found = authorRepository.findByName("Vi");

        Assertions.assertEquals(found, null);
        Assertions.assertNull(found);
    }

    @DisplayName("Test update author by id then return then return the author")
    @Test
    public void updateAuthorByIdTest(){
        Author existAuthor = new Author(1L, "John" , "Doe", null);
        Author updateAuthor = new Author(1L, "Jane", "Smith", null);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existAuthor));
        when(authorRepository.save(existAuthor)).thenReturn(updateAuthor);
        Author modified = authorService.updateAuthorById(1L, updateAuthor);

        Assertions.assertEquals(modified.getName(), "Jane");
        Assertions.assertEquals(modified.getLastName(), "Smith");

    }

    @DisplayName("Test update only name by id")
    @Test
    public void updateOnlyNameByIdTest(){
        Author existAuthor = new Author(1L, "John", "Doe", null);

        when(authorRepository.getReferenceById(1L)).thenReturn(existAuthor);
        when(authorRepository.save(existAuthor)).thenReturn(existAuthor);
        Author updated = authorService.updateOnlyNameById(1L, "Jane");

        Assertions.assertEquals(updated.getName(), "Jane");
    }

    @DisplayName("Test update only name by id when then return null because the user doesnt exists")
    @Test
    public void updateOnlyNameByIdTestReturnNull(){
        when(authorRepository.getReferenceById(2L)).thenReturn(null);
        Author updated = authorService.updateOnlyNameById(2L, "Jame");

        Assertions.assertNull(updated);
    }

    @DisplayName("Test function delete user by id")
    @Test
    public void deleteAuthorById(){
        when(authorRepository.findById(1L)).thenReturn(Optional.of(authorTest));
        authorService.deleteAuthorById(1L);

        verify(authorRepository, Mockito.times(1)).delete(authorTest);
    }
}
