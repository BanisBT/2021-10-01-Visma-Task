package com.tbarauskas.vismatask.entity;

import com.tbarauskas.vismatask.dto.BookRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "book")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "language")
    private String language;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "is_taken")
    private Boolean isTaken;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    public Book(BookRequestDTO bookDTO) {
        this.tittle = bookDTO.getTittle();
        this.author = bookDTO.getAuthor();
        this.category = bookDTO.getCategory();
        this.language = bookDTO.getLanguage();
        this.publicationDate = bookDTO.getPublicationDate();
        this.isbn = bookDTO.getIsbn();
        this.isTaken = bookDTO.getIsTaken();
    }
}
