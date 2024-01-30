package com.example.AutoresLibros.services;

import com.example.AutoresLibros.entities.Author;
import com.example.AutoresLibros.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImp implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author author) {
        if(author.getName() == null || author.getLastName() == null){
            throw new RuntimeException("Can be null");
        }

        if(!author.getLastName().contains("@")){
            throw new RuntimeException("The lastname must be contain @");
        }
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Author updateAuthorById(Long id, Author author) {
        Optional<Author> found = authorRepository.findById(id);
        found.ifPresent(user ->{
            user.setName(author.getName());
            user.setLastName(author.getLastName());
            authorRepository.save(user);
        });
        return found.orElse(null);
    }

    @Override
    public Author updateOnlyNameById(Long id, String name) {
        Author found = authorRepository.getReferenceById(id);
        if(found != null){
            found.setName(name);
            return authorRepository.save(found);
        }
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {
        Optional<Author> found = authorRepository.findById(id);
        found.ifPresent(author -> authorRepository.delete(author));
    }
}
