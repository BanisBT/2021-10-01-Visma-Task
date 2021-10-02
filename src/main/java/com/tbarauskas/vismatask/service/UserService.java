package com.tbarauskas.vismatask.service;

import com.tbarauskas.vismatask.entity.User;
import com.tbarauskas.vismatask.exception.UserHasAlreadyThreeBooksBorrowedException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void setBorrowedBookNumber(User user) {
        if (user.getBooksTake().equals(3)) {
            throw new UserHasAlreadyThreeBooksBorrowedException(user);
        } else {
            user.setBooksTake(user.getBooksTake() + 1);
        }
    }
}
