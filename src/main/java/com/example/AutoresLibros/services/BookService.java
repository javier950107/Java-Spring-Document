package com.example.AutoresLibros.services;

import com.example.AutoresLibros.entities.Book;
import com.example.AutoresLibros.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public List<Book> getAll();
    public Book insertBook(Book book);
}
