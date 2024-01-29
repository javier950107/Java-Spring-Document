package com.example.AutoresLibros.controllers;

import com.example.AutoresLibros.entities.Author;
import com.example.AutoresLibros.services.AuthorServiceImp;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.rmi.server.ExportException;

@RestController
public class AuthorController {

    @Autowired
    private AuthorServiceImp authorServiceImp;

    @GetMapping("/authors/get")
    public ResponseEntity<?> getAuthors(){
        try{
            return ResponseEntity.ok(authorServiceImp.getAuthors());
        }catch (Exception err){
            System.out.println(err.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, err.getMessage());
        }
    }

    @PostMapping("/authors/insert")
    public ResponseEntity<Author> insertAuthor(@RequestBody Author author){
        try{
            return ResponseEntity.ok(authorServiceImp.createAuthor(author));
        }catch (Exception err){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, err.getMessage());
        }
    }

    @GetMapping("/authors/{name}")
    public ResponseEntity<Author> getAuthorsByName(@PathVariable String name){
        try{
            return ResponseEntity.ok(authorServiceImp.getAuthorByName(name));
        }catch (Exception err){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, err.getMessage());
        }
    }

    @PutMapping("/authors/update")
    public ResponseEntity<?> updateAuthorById(@RequestBody Author author){
        try{
            return ResponseEntity.ok(authorServiceImp.updateAuthorById(author.getId(), author));
        }catch (Exception err){
            System.out.println(err.getMessage());
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/authors/update/name")
    public ResponseEntity<?> updateOnlyNameById(@RequestBody Author author){
        try{
            return ResponseEntity.ok(authorServiceImp.updateOnlyNameById(author.getId(), author.getName()));
        }catch (Exception err){
            System.out.println(err.getMessage());
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/authors/delete/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id){
        try{
            authorServiceImp.deleteAuthorById(id);
            return ResponseEntity.ok("User deleted");
        }catch (Exception err){
            System.out.println(err.getMessage());
            return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
