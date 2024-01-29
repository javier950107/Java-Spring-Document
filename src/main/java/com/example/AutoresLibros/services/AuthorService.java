package com.example.AutoresLibros.services;

import com.example.AutoresLibros.entities.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public Author createAuthor(Author author);
    public List<Author> getAuthors();
    public Author getAuthorByName(String name);
    public Author updateAuthorById(Long id, Author author);
    public Author updateOnlyNameById(Long id, String name);
    public void deleteAuthorById(Long id);
}
