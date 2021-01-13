package com.example.books.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.books.dto.BookDto;
import com.example.books.controller.BookController;
import com.example.books.service.BookService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/api/bookshelf")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    
    @PostMapping
    public ResponseEntity<BookDto> saveBook(@RequestHeader("Authorization") String authorization ,@RequestBody BookDto bookDto) {
    	return ResponseEntity.status(HttpStatus.CREATED)
    	.body(bookService.save(bookDto,authorization));
    }
    
    
    @GetMapping ("/userbooks")
    public ResponseEntity<List<BookDto>> getUserBooks(@RequestHeader("Authorization") String authorization) {
    	return ResponseEntity.status(HttpStatus.OK)
    			.body(bookService.getUserBooks(authorization));
    }
    
}
