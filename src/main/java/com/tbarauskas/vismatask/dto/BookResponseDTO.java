package com.tbarauskas.vismatask.dto;

import com.tbarauskas.vismatask.entity.Book;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookResponseDTO {

    private Long id;

    private String tittle;

    private String author;

    private String category;

    private String language;

    private LocalDate publicationDate;

    private String isbn;

    public BookResponseDTO(Book book) {
        this.id = book.getId();
        this.tittle = book.getTittle();
        this.author = book.getAuthor();
        this.category = book.getCategory();
        this.language = book.getLanguage();
        this.publicationDate = book.getPublicationDate();
        this.isbn = book.getIsbn();
    }
}
