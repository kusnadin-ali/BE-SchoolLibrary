package com.smbc.schoollibrary.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "book_catalog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCatalog implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column()
    private String title;

    @Column(name = "book_code")
    private String bookCode;

    @Column()
    private String author;

    @Column()
    private String publisher;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column()
    private String genre;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
