package com.smbc.schoollibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRentDto {
    private String studentNumber;
    private String bookCode;
}
