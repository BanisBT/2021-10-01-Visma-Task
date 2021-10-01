package com.tbarauskas.vismatask.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequestDTO {

    private String tittle;

    private String author;

    private String category;

    private String language;

    private LocalDate publicationDate;

    private String isbn;

    private String guid;
}
