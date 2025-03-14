package com.smbc.schoollibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smbc.schoollibrary.constants.StatusEnum;
import com.smbc.schoollibrary.models.BookRents;

@Repository
public interface BookRentsRepository extends JpaRepository<BookRents, Long> {
    
    Optional<BookRents> findByStudentNumberAndBookCodeAndStatus(String StudentNumber, String BookCode, StatusEnum status);

    List<BookRents> findByStatus(StatusEnum status);
}
