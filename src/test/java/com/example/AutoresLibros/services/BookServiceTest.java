package com.example.AutoresLibros.services;


import com.example.AutoresLibros.entities.Author;
import com.example.AutoresLibros.entities.Book;
import com.example.AutoresLibros.repositories.AuthorRepository;
import com.example.AutoresLibros.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImp bookService;

    @DisplayName("Test get all users then return a list of books")
    @Test
    public void findAllBooksTest(){
        Book book1 = Book.builder().name("John").build();
        Book book2 = Book.builder().name("Doe").build();

        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));
        List<Book> books = bookService.getAll();

        Assertions.assertEquals(books.size(), 2);
    }

    @DisplayName("Test when insert a book then return that bookd")
    @Test
    public void insertBookTest(){
        Author author = new Author(1L,"John", "Doe", null);
        Book book1 = Book.builder().name("John").author(author).build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(book1)).thenReturn(book1);
        Book bookInserted = bookService.insertBook(book1);

        Assertions.assertEquals(bookInserted.getName(), "John");
    }
}
