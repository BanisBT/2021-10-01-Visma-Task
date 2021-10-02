package com.tbarauskas.vismatask.exception;

import com.tbarauskas.vismatask.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserHasAlreadyThreeBooksBorrowedException extends RuntimeException{

    private User user;
}
