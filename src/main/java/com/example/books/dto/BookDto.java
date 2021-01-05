package com.example.books.dto;

import com.example.books.dto.BookDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfBooks;
}
