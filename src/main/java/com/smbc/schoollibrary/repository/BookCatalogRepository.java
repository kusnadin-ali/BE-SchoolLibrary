package com.smbc.schoollibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smbc.schoollibrary.models.BookCatalog;

@Repository
public interface BookCatalogRepository extends JpaRepository<BookCatalog, Long> {
    
    List<BookCatalog> findAllByIsDeletedFalse();

    Optional<BookCatalog> findByIdAndIsDeletedFalse(Long id);

    Optional<BookCatalog> findByBookCode(String bookCode);
}
