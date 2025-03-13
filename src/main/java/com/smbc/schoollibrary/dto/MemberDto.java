package com.smbc.schoollibrary.dto;

import com.smbc.schoollibrary.constants.GenderEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    
    private String fullname;
    private String studentNumber;
    private GenderEnum gender;
}
