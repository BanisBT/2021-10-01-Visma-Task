package com.tbarauskas.vismatask.service;

import com.tbarauskas.vismatask.entity.Book;
import com.tbarauskas.vismatask.entity.User;
import com.tbarauskas.vismatask.exception.BookMaxBorrowingPeriodIsTwoMonthException;
import com.tbarauskas.vismatask.exception.BookNotFoundException;
import com.tbarauskas.vismatask.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private final LocalDate today = LocalDate.now();

    @Mock
    private Book book;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookService bookService;

    @Test
    void testCreateBook() {
        bookService.createBook(book);

        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testTakeBookThenLendingPeriodCorrect() {
        User user = new User();

        bookService.takeBook(user, today.plusMonths(1));

        verify(userService, times(1)).setBorrowedBookNumber(user);
    }

    @Test
    void testThenLendingPeriodIsNotCorrect() {
        assertThrows(BookMaxBorrowingPeriodIsTwoMonthException.class,
                () -> bookService.isLendingPeriodCorrect(today.plusMonths(3)));
    }

    @Test
    void testGetBookById() {
        when(bookRepository.getBookById(1L)).thenReturn(Optional.of(book));

        bookService.getBookById(1L);

        verify(bookRepository, times(1)).getBookById(1L);
    }

    @Test
    void testGetBookByNotExistedId() {
        when(bookRepository.getBookById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1000L));
    }

    @Test
    void testGetAllBooksByAuthor() {
        bookService.getBooksByAuthor("name", null);

        verify(bookRepository, times(1)).getAllByAuthor("name");
        verify(bookRepository, times(0)).getAllByAuthorAndIsTaken("name", null);
    }

    @Test
    void testGetBooksByAuthorIfAvailable() {
        bookService.getBooksByAuthor("name", false);

        verify(bookRepository, times(0)).getAllByAuthor("name");
        verify(bookRepository, times(1)).getAllByAuthorAndIsTaken("name", false);
    }

    @Test
    void deleteBook() {
        when(bookRepository.getBookById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).getBookById(1L);
        verify(bookRepository, times(1)).deleteById(book.getId());
    }
}
