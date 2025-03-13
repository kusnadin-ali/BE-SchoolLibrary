package com.smbc.schoollibrary.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smbc.schoollibrary.dto.BookDto;
import com.smbc.schoollibrary.services.BookCatalogService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/book-catalog")
@RequiredArgsConstructor
public class BookCatalogController {
    
    private final BookCatalogService bookCatalogService;
    
    @GetMapping
    public ResponseEntity<?> getAllBookCatalog() {
        return bookCatalogService.getAllBookCatalog();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookCatalogById(@PathVariable Long id) {
        return bookCatalogService.getBookCatalogById(id);
    }

    @PostMapping
    public ResponseEntity<?> addNewBookCatalog(@RequestBody BookDto request) {
        return bookCatalogService.addNewBookCatalog(request);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookCatalog(@PathVariable Long id, @RequestBody BookDto request) {
        return bookCatalogService.updateBookCatalog(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookCatalog(@PathVariable Long id) {
        return bookCatalogService.deleteBookCatalog(id);
    }
}
