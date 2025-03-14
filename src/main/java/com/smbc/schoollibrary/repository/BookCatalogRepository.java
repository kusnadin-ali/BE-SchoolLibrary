package com.smbc.schoollibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smbc.schoollibrary.dto.ListBookPojo;
import com.smbc.schoollibrary.models.BookCatalog;

@Repository
public interface BookCatalogRepository extends JpaRepository<BookCatalog, Long> {

    List<BookCatalog> findAllByIsDeletedFalse();

    Optional<BookCatalog> findByIdAndIsDeletedFalse(Long id);

    Optional<BookCatalog> findByBookCode(String bookCode);

    @Query(value = """
            SELECT
                bc.id,
                bc.book_code AS bookCode,
                bc.title,
                bc.genre,
                bc.author,
                bc.publisher,
                bc.publish_date AS publishDate,
            CASE
                WHEN MAX(br.status) IN ('RENTED', 'OVERDUE') THEN 'Not Available'
            ELSE 'Available'
                END AS bookStatus
            FROM book_catalog bc
            LEFT JOIN book_rents br ON bc.book_code = br.book_code
                AND br.status IN ('RENTED', 'OVERDUE')
            WHERE bc.is_deleted = 0
            GROUP BY bc.book_code, bc.title, bc.genre, bc.author, bc.publisher, bc.publish_date
            HAVING (:availability IS NULL OR bookStatus = :availability);
                    """, nativeQuery = true)
    List<ListBookPojo> getAllWithAvailability(@Param("availability") String availability);
}
