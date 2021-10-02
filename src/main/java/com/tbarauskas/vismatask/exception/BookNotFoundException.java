package com.tbarauskas.vismatask.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookNotFoundException extends RuntimeException{

    private final Long id;
}
