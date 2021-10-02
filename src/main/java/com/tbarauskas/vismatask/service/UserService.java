package com.tbarauskas.vismatask.service;

import com.tbarauskas.vismatask.entity.User;
import com.tbarauskas.vismatask.exception.UserHasAlreadyThreeBooksBorrowedException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void setBorrowedBookNumber(User user) {
        if (user.getBooksTake() < 3) {
            user.setBooksTake(user.getBooksTake() + 1);
        } else {
            throw new UserHasAlreadyThreeBooksBorrowedException(user);
        }
    }
}
