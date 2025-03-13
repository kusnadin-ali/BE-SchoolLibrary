package com.smbc.schoollibrary.services;

import org.springframework.stereotype.Service;

import com.smbc.schoollibrary.repository.MembersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembersService {
    private final MembersRepository membersRepository; 

    
}
