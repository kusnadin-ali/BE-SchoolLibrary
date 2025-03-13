package com.smbc.schoollibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smbc.schoollibrary.models.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, Long>{
    List<Members> findAll();

    Optional<Members> findById(Long id);

    Optional<Members> findByIdAndIsDeletedFalse(Long id);

    Optional<Members> findByStudentNumber(String studentNumber);
}
