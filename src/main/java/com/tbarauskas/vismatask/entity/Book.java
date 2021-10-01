package com.tbarauskas.vismatask.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "book")
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

    @Column(name = "guid")
    private String guid;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;
}
