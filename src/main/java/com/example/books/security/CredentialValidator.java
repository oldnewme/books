package com.example.books.security;

import com.example.books.dto.LoginRequest;
import com.example.books.exceptions.BookApplicationException;
import com.example.books.model.User;
import com.example.books.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional(propagation = Propagation.REQUIRED)
public class CredentialValidator {
	
	@Autowired
    private UserRepository userRepository;
    
    @Autowired 
    PasswordEncoder passwordEncoder;

	
	public boolean validCredentials(LoginRequest loginRequest) throws BookApplicationException {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		if(usernameRegistered(username)) {
			if(validPassword(username, password)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean usernameRegistered(String username) throws BookApplicationException {
		if(userRepository.findByUsername(username) == null) {
			throw new BookApplicationException("invalid username");
		}
		else {
			return true;
		}
	}

	private boolean validPassword(String username, String password) throws BookApplicationException {
		User user = userRepository.findByUsername(username);
		if(passwordEncoder.matches(password, user.getPassword())) {
			return true;
		} else {
			throw new BookApplicationException("invalid password");
		}
	}
	
}