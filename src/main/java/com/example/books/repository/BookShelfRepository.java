package com.example.books.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.books.model.BookShelf;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {

    Optional<BookShelf> findByName(String bookShelfName);
}
