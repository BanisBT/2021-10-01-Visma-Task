package com.tbarauskas.vismatask.controller;

import com.tbarauskas.vismatask.dto.BookRequestDTO;
import com.tbarauskas.vismatask.dto.BookResponseDTO;
import com.tbarauskas.vismatask.entity.Book;
import com.tbarauskas.vismatask.entity.User;
import com.tbarauskas.vismatask.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO createBook(BookRequestDTO bookDTO) {
        Book createBook = bookService.createBook(new Book(bookDTO));
        log.debug("Book - {} has been successfully created", createBook);
        return new BookResponseDTO(bookService.createBook(createBook));
    }

    @PatchMapping("/take")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void takeBook(User user, LocalDate lendingDateTo) {
        bookService.takeBook(user, lendingDateTo);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDTO getBookById(@PathVariable Long id){
        return modelMapper.map(bookService.getBookById(id), BookResponseDTO.class);
    }

    @GetMapping("/author/{authorName}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponseDTO> getBooksByAuthor(@PathVariable String authorName){
        return bookService.getBooksByAuthor(authorName).stream()
                .map(BookResponseDTO::new)
                .collect(Collectors.toList());
    }
}
