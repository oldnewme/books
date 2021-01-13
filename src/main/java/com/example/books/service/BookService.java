package com.example.books.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.books.dto.BookDto;
import com.example.books.exceptions.BookApplicationException;
import com.example.books.model.Book;
import com.example.books.model.User;
import com.example.books.repository.BookRepository;
import com.example.books.repository.UserRepository;
import com.example.books.security.JwtValidator;
import com.example.books.service.BookService;


@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	UserRepository userRespository;

	@Autowired
	private JwtValidator jwtValidator;

	@Transactional
	public BookDto save(BookDto bookDto, String authorization) throws BookApplicationException {
		String token = jwtValidator.getTokenFromRequest(authorization);
		if (jwtValidator.isValid(token)) {
			User user = userRespository.findByUsername(jwtValidator.getUsernameFromToken(token));

			Book b = Book.builder().name(bookDto.getName()).description(bookDto.getDescription()).user(user).build();
			Book save = bookRepository.save(b);
			bookDto.setId(save.getId());
			return bookDto;
		} else {
			throw new BookApplicationException("FAILED SAVING BOOK");
		}
	}

	@Transactional(readOnly = true)
	public List<BookDto> getUserBooks(String authorization) throws BookApplicationException {
		String token = jwtValidator.getTokenFromRequest(authorization);
		if (jwtValidator.isValid(token)) {
			User user = userRespository.findByUsername(jwtValidator.getUsernameFromToken(token));

			Iterator<Book> userBooks = bookRepository.findAllUserBooks(user.getUserId()).iterator();
			List<BookDto> userBooksDto = new ArrayList<BookDto>();
			while (userBooks.hasNext()) {
				Book b = userBooks.next();
				userBooksDto
						.add(BookDto.builder().name(b.getName()).description(b.getDescription()).id(b.getId()).build());
			}

			return userBooksDto;
		} else {
			throw new BookApplicationException("FAILED GETTING ALL USER BOOKS");
		}
	}

}
