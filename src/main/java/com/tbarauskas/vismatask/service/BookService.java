package com.tbarauskas.vismatask.service;

import com.tbarauskas.vismatask.entity.Book;
import com.tbarauskas.vismatask.entity.User;
import com.tbarauskas.vismatask.exception.BookMaxBorrowingPeriodIsTwoMonth;
import com.tbarauskas.vismatask.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookService {

    private final LocalDate localDatePlus = LocalDate.now().plusMonths(2);

    private final BookRepository bookRepository;

    private final UserService userService;

    public BookService(BookRepository bookRepository, UserService userService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public void takeBook(User user, LocalDate lendingDateTo) {
        isLendingPeriodCorrect(lendingDateTo);
        userService.setBorrowedBookNumber(user);
    }

    public void isLendingPeriodCorrect(LocalDate lendingDateTo) {
        if (lendingDateTo.isAfter(localDatePlus)) {
            throw new BookMaxBorrowingPeriodIsTwoMonth(localDatePlus);
        }
    }
}
