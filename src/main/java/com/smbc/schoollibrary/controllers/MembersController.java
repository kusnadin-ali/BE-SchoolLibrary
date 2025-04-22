package com.smbc.schoollibrary.controllers;

import com.smbc.schoollibrary.dto.MemberDto;
import com.smbc.schoollibrary.dto.SMBCResponseDto;
import com.smbc.schoollibrary.services.MembersService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MembersController {
    
    private final MembersService membersService;

    @GetMapping
    public SMBCResponseDto<Object> getAllMember() {
        return membersService.getAllMember();
    }

    @GetMapping("/{id}")
    public SMBCResponseDto<Object> getDetailMember(@PathVariable Long id) {
        return membersService.getMemberById(id);
    }
    
    @PostMapping
    public SMBCResponseDto<Object> createNewMember(@RequestBody MemberDto request) {
        return membersService.createMember(request);
    }

    @PutMapping("/{id}")
    public SMBCResponseDto<Object> editMember(@PathVariable Long id, @RequestBody MemberDto request) {
        return membersService.updateMember(id, request);
    }

    @DeleteMapping("/{id}")
    public SMBCResponseDto<Object> deleteMember(@PathVariable Long id) {
        return membersService.deleteMember(id);
    }
    
}
