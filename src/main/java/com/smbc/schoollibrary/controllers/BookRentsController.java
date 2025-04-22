package com.smbc.schoollibrary.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smbc.schoollibrary.dto.BookRentDto;
import com.smbc.schoollibrary.dto.SMBCResponseDto;
import com.smbc.schoollibrary.services.BookRentsService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/book-rents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookRentsController {

    
    private final BookRentsService bookRentsService;

    @GetMapping()
    public SMBCResponseDto<Object> getAllBookRent() {
        return bookRentsService.getAllBookRents();
    }

    @PostMapping("/return-book")
    public SMBCResponseDto<Object> returnBook(@RequestBody BookRentDto requeest) {
        return bookRentsService.returnBook(requeest);
    }

    @PostMapping()
    public SMBCResponseDto<Object> createNewBookRent(@RequestBody BookRentDto request) {
        return bookRentsService.createNewBookRent(request);
    }
    
    
}
