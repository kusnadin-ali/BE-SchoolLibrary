package com.smbc.schoollibrary.dto;

import java.time.LocalDate;

public interface ListBookPojo {
    String getBookCode();

    String getTitle();

    String getGenre();

    String getAuthor();

    String getPublisher();

    LocalDate getPublishDate();

    String getBookStatus();
}
