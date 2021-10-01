package com.tbarauskas.vismatask.service;

import com.tbarauskas.vismatask.entity.Book;
import com.tbarauskas.vismatask.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }
}
