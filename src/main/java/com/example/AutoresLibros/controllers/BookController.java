package com.example.AutoresLibros.controllers;

import com.example.AutoresLibros.entities.Book;
import com.example.AutoresLibros.services.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BookController {

    @Autowired
    private BookServiceImp bookService;

    @PostMapping("/books/insert")
    public ResponseEntity<?> insertBook(@RequestBody Book book){
        try{
            return ResponseEntity.ok(bookService.insertBook(book));
        }catch (Exception err){
            System.out.println(err.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, err.getMessage());
        }
    }
}
