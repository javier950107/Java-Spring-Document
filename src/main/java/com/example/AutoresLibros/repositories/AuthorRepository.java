package com.example.AutoresLibros.repositories;

import com.example.AutoresLibros.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Author findByName(String name);
}
