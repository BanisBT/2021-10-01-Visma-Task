package com.tbarauskas.vismatask.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequestDTO {

    private final String tittle;

    private final String author;

    private final String category;

    private final String language;

    private final LocalDate publicationDate;

    private final String isbn;

    private final Boolean isTaken;
}
