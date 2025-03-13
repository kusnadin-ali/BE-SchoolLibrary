package com.smbc.schoollibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smbc.schoollibrary.models.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, Long>{
    
}
