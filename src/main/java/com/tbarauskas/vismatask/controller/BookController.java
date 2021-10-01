package com.tbarauskas.vismatask.controller;

import com.tbarauskas.vismatask.dto.BookRequestDTO;
import com.tbarauskas.vismatask.dto.BookResponseDTO;
import com.tbarauskas.vismatask.entity.Book;
import com.tbarauskas.vismatask.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO createBook(BookRequestDTO bookDTO) {
        Book createBook = bookService.createBook(new Book(bookDTO));
        log.debug("Book - {} has been successfully created", createBook);
        return new BookResponseDTO(bookService.createBook(createBook));
    }
}
