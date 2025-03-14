package com.smbc.schoollibrary.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import com.smbc.schoollibrary.constants.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "book_rents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRents implements Serializable{
    
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "student_number")
    private String studentNumber;

    @Column(name = "book_code")
    private String bookCode;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
