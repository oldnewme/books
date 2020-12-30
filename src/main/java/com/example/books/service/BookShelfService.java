package com.example.books.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.books.dto.BookShelfDto;
import com.example.books.exceptions.DemoApplicationException;
import com.example.books.model.BookShelf;
import com.example.books.repository.BookShelfRepository;
import com.example.books.service.BookShelfService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BookShelfService {

    private final BookShelfRepository bookShelfRepository;
    //private final BookShelfMapper BookShelfMapper;
	
    @Transactional
	public BookShelfDto save(BookShelfDto bookShelfDto) {
		BookShelf save = bookShelfRepository.save(mapBookShelfDto(bookShelfDto));
		bookShelfDto.setId(save.getId());
		return bookShelfDto;
	}
    
	@Transactional(readOnly = true)
	public List<BookShelfDto> getAll() {
		return bookShelfRepository.findAll()
			.stream()
			.map(this::mapToDto)
			.collect(toList());
		
	}
	
	private BookShelfDto mapToDto(BookShelf bookShelf) {
		return BookShelfDto.builder().name(bookShelf.getName())
				.id(bookShelf.getId())
				.numberOfBooks(bookShelf.getPosts().size())
				.build();
	}

	private BookShelf mapBookShelfDto(BookShelfDto bookShelfDto) {
		return BookShelf.builder().name(bookShelfDto.getName())
		.description(bookShelfDto.getDescription())
		.build();
		
	}
	

	/*
    @Transactional
    public BookShelfDto save(BookShelfDto BookShelfDto) {
        BookShelf save = BookShelfRepository.save(BookShelfMapper.mapDtoToBookShelf(BookShelfDto));
        BookShelfDto.setId(save.getId());
        return BookShelfDto;
    }

    @Transactional(readOnly = true)
    public List<BookShelfDto> getAll() {
        return BookShelfRepository.findAll()
                .stream()
                .map(BookShelfMapper::mapBookShelfToDto)
                .collect(toList());
    }

    public BookShelfDto getBookShelf(Long id) {
        BookShelf BookShelf = BookShelfRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No BookShelf found with ID - " + id));
        return BookShelfMapper.mapBookShelfToDto(BookShelf);
    }
    */
}
