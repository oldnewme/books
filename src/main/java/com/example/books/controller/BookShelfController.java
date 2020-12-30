package com.example.books.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.books.dto.BookShelfDto;
import com.example.books.controller.BookShelfController;
import com.example.books.service.BookShelfService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/bookshelf")
@AllArgsConstructor
@Slf4j
public class BookShelfController {

    private final BookShelfService bookShelfService;
    
    @PostMapping
    public ResponseEntity<BookShelfDto> createBookShelf(@RequestBody BookShelfDto bookShelfDto) {
    	return ResponseEntity.status(HttpStatus.CREATED)
    	.body(bookShelfService.save(bookShelfDto));
    }
    
    @GetMapping
    public ResponseEntity<List<BookShelfDto>> getAllBookShelfs() {
    	return ResponseEntity.status(HttpStatus.OK)
    			.body(bookShelfService.getAll());
    }
    /*
    @PostMapping
    public ResponseEntity<BookShelfDto> createBookShelf(@RequestBody BookShelfDto bookShelfDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookShelfDto.save(bookShelfDto));
    }
    */
/*
    @GetMapping
    public ResponseEntity<List<BookShelfDto>> getAllBookShelfs() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BookShelfService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookShelfDto> getBookShelf(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BookShelfService.getBookShelf(id));
    }
    */
}
