package com.example.books.dto;

import com.example.books.dto.BookShelfDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookShelfDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfBooks;
}
