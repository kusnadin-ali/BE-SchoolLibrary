package com.smbc.schoollibrary.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smbc.schoollibrary.constants.StatusBookEnum;
import com.smbc.schoollibrary.dto.BookDto;
import com.smbc.schoollibrary.dto.SMBCResponseDto;
import com.smbc.schoollibrary.services.BookCatalogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/book-catalog")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class BookCatalogController {

    private final BookCatalogService bookCatalogService;

    @GetMapping
    public SMBCResponseDto<Object> getAllBookCatalog(
            @RequestParam(required = false) StatusBookEnum availability) {
        return bookCatalogService.getAllBookCatalog(availability);
    }

    @GetMapping("/{id}")
    public SMBCResponseDto<Object> getBookCatalogById(@PathVariable Long id) {
        return bookCatalogService.getBookCatalogById(id);
    }

    @PostMapping
    public SMBCResponseDto<Object> addNewBookCatalog(@RequestBody BookDto request) {
        return bookCatalogService.addNewBookCatalog(request);
    }

    @PutMapping("/{id}")
    public SMBCResponseDto<Object>  updateBookCatalog(@PathVariable Long id, @RequestBody BookDto request) {
        return bookCatalogService.updateBookCatalog(id, request);
    }

    @DeleteMapping("/{id}")
    public SMBCResponseDto<Object>  deleteBookCatalog(@PathVariable Long id) {
        return bookCatalogService.deleteBookCatalog(id);
    }
}
