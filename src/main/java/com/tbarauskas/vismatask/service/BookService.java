package com.tbarauskas.vismatask.service;

import com.tbarauskas.vismatask.entity.Book;
import com.tbarauskas.vismatask.entity.User;
import com.tbarauskas.vismatask.exception.BookMaxBorrowingPeriodIsTwoMonthException;
import com.tbarauskas.vismatask.exception.BookNotFoundException;
import com.tbarauskas.vismatask.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    private final LocalDate localDatePlus = LocalDate.now().plusMonths(2);

    private final BookRepository bookRepository;

    private final UserService userService;

    public BookService(BookRepository bookRepository, UserService userService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public void takeBook(User user, LocalDate lendingDateTo) {
        isLendingPeriodCorrect(lendingDateTo);
        userService.setBorrowedBookNumber(user);
    }

    public void isLendingPeriodCorrect(LocalDate lendingDateTo) {
        if (lendingDateTo.isAfter(localDatePlus)) {
            throw new BookMaxBorrowingPeriodIsTwoMonthException(localDatePlus);
        }
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> getBooksByAuthor(String authorName, Boolean isNotTaken) {
        if (isNotTaken == null) {
            return bookRepository.getAllByAuthor(authorName);
        }
        return bookRepository.getAllByAuthorAndIsTaken(authorName, isNotTaken);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(getBookById(id).getId());
    }
}
