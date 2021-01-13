package com.example.books.service;

import com.example.books.dto.AuthenticationResponse;
import com.example.books.dto.LoginRequest;
import com.example.books.dto.RegisterRequest;
import com.example.books.exceptions.BookApplicationException;
import com.example.books.model.User;
import com.example.books.repository.UserRepository;
import com.example.books.security.CredentialValidator;
import com.example.books.security.JwtGenerator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;


@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CredentialValidator credentialValidator;

    @Autowired
    private JwtGenerator jwtGenerator;
	
	@Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());

        userRepository.save(user);
       
    }
	
	public AuthenticationResponse login(LoginRequest loginRequest) throws BookApplicationException {

        if(credentialValidator.validCredentials(loginRequest)){
            User user = userRepository.findByUsername(loginRequest.getUsername());
            String token = jwtGenerator.generateToken(user);
            return AuthenticationResponse.builder()
                .authenticationToken(token)
                .username(loginRequest.getUsername())
                .build();
        } else {
            throw new BookApplicationException("LOGIN FAIL");
        }

		
	}
    
}
