package com.example.books.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.books.dto.BookDto;
import com.example.books.exceptions.DemoApplicationException;
import com.example.books.model.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    //private final BookShelfMapper BookShelfMapper;
    
    private final AuthService authService;
	
    @Transactional
	public BookDto save(BookDto bookDto) {
		Book save = bookRepository.save(mapBookDto(bookDto));
		bookDto.setId(save.getId());
		return bookDto;
	}
    
	@Transactional(readOnly = true)
	public List<BookDto> getAll() {
		return bookRepository.findAll()
			.stream()
			.map(this::mapToDto)
			.collect(toList());
		
	}
	
	@Transactional(readOnly = true)
	public List<BookDto> getUserBooks(){
		return bookRepository.findAllUserBooks(authService.getCurrentUser().getUserId())
				.stream()
				.map(this::mapToDto)
				.collect(toList());
	}
	
	private BookDto mapToDto(Book bookShelf) {
		return BookDto.builder().name(bookShelf.getName())
				.description(bookShelf.getDescription())
				.id(bookShelf.getId())
				.build();
	}

	private Book mapBookDto(BookDto bookShelfDto) {
		return Book.builder().name(bookShelfDto.getName())
		.description(bookShelfDto.getDescription())
		.user(authService.getCurrentUser())
		.build();
		
	}
}
