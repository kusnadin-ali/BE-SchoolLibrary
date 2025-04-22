package com.smbc.schoollibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMBCResponseDto<T> {
    private String message;

    private T result;

    private String code;
}
