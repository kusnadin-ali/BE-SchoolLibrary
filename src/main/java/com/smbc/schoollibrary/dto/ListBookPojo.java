package com.smbc.schoollibrary.dto;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ListBookPojo {
    
    private Integer id;

    private String bookCode;

    private String title;

    private String genre;

    private String author;

    private String publisher;

    private Date publishDate;

    private String bookStatus;

    public ListBookPojo(Integer id, String bookCode, String title, String genre, String author, String publisher, Date publishDate, String bookStatus) {
        this.id = id;
        this.bookCode = bookCode;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.bookStatus = bookStatus;
    }
}
