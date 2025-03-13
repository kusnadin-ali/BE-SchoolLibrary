package com.smbc.schoollibrary.models;

import java.io.Serial;
import java.io.Serializable;

import com.smbc.schoollibrary.constants.GenderEnum;

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

@Entity(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Members implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -1L; 
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column()
    private String fullname;

    @Column(name = "student_number")
    private String studentNumber;

    @Column()
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;


    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
