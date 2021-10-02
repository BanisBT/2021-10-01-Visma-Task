package com.tbarauskas.vismatask.exception;

import com.tbarauskas.vismatask.model.ErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorHandler> exceptionHandler(BookNotFoundException e) {
        log.debug("Book with id - {} was not found", e.getId());
        return new ResponseEntity<>(new ErrorHandler(HttpStatus.NOT_FOUND.value(),
                String.format("Book with id %d was not found", e.getId())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookMaxBorrowingPeriodIsTwoMonthException.class)
    public ResponseEntity<ErrorHandler> exceptionHandler(BookMaxBorrowingPeriodIsTwoMonthException e){
        return new ResponseEntity<>(new ErrorHandler(HttpStatus.FORBIDDEN.value(),
                String.format("Deadline to return book is - %s", e.getMaxBorrowingPeriod())), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserHasAlreadyThreeBooksBorrowedException.class)
    public ResponseEntity<ErrorHandler> exceptionHandler(UserHasAlreadyThreeBooksBorrowedException e){
        log.debug("User - {} already has three books borrowed", e.getUser());
        return new ResponseEntity<>(new ErrorHandler(HttpStatus.FORBIDDEN.value(),
                "You already have borrowed three books"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorHandler> exceptionHandler(Exception e) {
        log.error("Unexpected error - {}", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
