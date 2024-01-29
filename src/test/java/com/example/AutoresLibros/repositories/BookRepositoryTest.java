package com.example.AutoresLibros.repositories;


import com.example.AutoresLibros.entities.Author;
import com.example.AutoresLibros.entities.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("Test when insert a user")
    @Test
    public void insertUserTest(){
        Book book1 = new Book(1L, "YOu", null);

        Book inserted = bookRepository.save(book1);

        Assertions.assertEquals(inserted.getName(), "YOu");
    }

    @DisplayName("Show the users test")
    @Test
    public void findAllUsersTest(){
        Book book1 = Book.builder().name("There").build();
        Book book2 = Book.builder().name("You").build();
        //Book book2 = new Book(3L, "There", null);

        bookRepository.save(book1);
        bookRepository.save(book2);
        List<Book> books = bookRepository.findAll();

        Assertions.assertEquals(books.size(), 2);
    }

}
