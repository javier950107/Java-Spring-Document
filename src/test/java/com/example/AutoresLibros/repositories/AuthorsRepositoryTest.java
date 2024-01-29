package com.example.AutoresLibros.repositories;

import com.example.AutoresLibros.entities.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class AuthorsRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    public Author author;

    @BeforeEach
    void setUp(){
        author = new Author(1L, "John" , "Doe", null);
    }

    @DisplayName("Test on insert author repository")
    @Test
    public void insertAuthor(){
        Author inserted = authorRepository.save(author);
        Assertions.assertEquals(inserted.getName(), "John");
    }

    @DisplayName("Test when find all users")
    @Test
    public void getAllAuthors(){
        //given
        Author newAuthor = new Author(2L,"Gen", "Gon", null);

        //when
        authorRepository.save(newAuthor);
        authorRepository.save(author);
        List<Author> authors = authorRepository.findAll();

        //then
        Assertions.assertEquals(authors.size(), 2);
    }

    @DisplayName("Test find by id")
    @Test
    public void findByIdTest(){
        authorRepository.save(author);
        Optional<Author> found = authorRepository.findById(author.getId());
        Assertions.assertEquals(found.get().getId(), author.getId());
    }
}
