package com.tbarauskas.vismatask.repository;

import com.tbarauskas.vismatask.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testGetBookById() {
        Book book = bookRepository.getBookById(1L).orElse(null);

        assert book != null;
        assertEquals(1L, book.getId());
    }

    @Test
    void testGetAllByAuthor() {
        List<Book> bookList = bookRepository.getAllByAuthor("George Orwell");

        assertEquals(2, bookList.size());
    }

    @Test
    void testGetAllByAuthorAndIsTaken() {
        List<Book> bookList = bookRepository.getAllByAuthorAndIsTaken("George Orwell", true);

        assertEquals(1, bookList.size());
    }
}
