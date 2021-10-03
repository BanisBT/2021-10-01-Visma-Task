package com.tbarauskas.vismatask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorHandler {

    private final Integer status;

    private final String message;
}
