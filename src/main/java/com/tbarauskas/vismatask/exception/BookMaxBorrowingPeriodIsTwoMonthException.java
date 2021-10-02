package com.tbarauskas.vismatask.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BookMaxBorrowingPeriodIsTwoMonthException extends RuntimeException {

    private final LocalDate maxBorrowingPeriod;
}
