package com.example.AutoresLibros.services;

import com.example.AutoresLibros.entities.Author;
import com.example.AutoresLibros.entities.Book;
import com.example.AutoresLibros.repositories.AuthorRepository;
import com.example.AutoresLibros.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book insertBook(Book book) {
        Optional<Author> author = authorRepository.findById(book.getAuthor().getId());
        if(author.isPresent()){
            return bookRepository.save(book);
        }
        return null;
    }
}
