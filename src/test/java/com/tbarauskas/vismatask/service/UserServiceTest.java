package com.tbarauskas.vismatask.service;

import com.tbarauskas.vismatask.entity.User;
import com.tbarauskas.vismatask.exception.UserHasAlreadyThreeBooksBorrowedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private User user;

    @InjectMocks
    private UserService userService;

    @Test
    void testSetBorrowedBookNumberIfUserHasThreeBooksTaken() {
        when(user.getBooksTake()).thenReturn(3);

        assertThrows(UserHasAlreadyThreeBooksBorrowedException.class, () -> userService.setBorrowedBookNumber(user));
    }
}
