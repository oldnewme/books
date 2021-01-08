package com.example.books.repository;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.books.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String bookShelfName);
   
    
    @Query(
    		  value = "SELECT * FROM book WHERE user_user_id = :user_id ", 
    		  nativeQuery = true)
    		Collection<Book> findAllUserBooks(@Param("user_id") Long user_id);
    
  
}
