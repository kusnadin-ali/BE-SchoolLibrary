package com.smbc.schoollibrary.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private String bookCode;
    private LocalDate publishDate;
}
