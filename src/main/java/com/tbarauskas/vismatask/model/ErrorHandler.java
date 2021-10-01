package com.tbarauskas.vismatask.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorHandler {

    private final Integer status;

    private final String message;
}
