package com.POS.demo.Service;

import com.POS.demo.Exceptions.UserException;
import com.POS.demo.modal.User;

import java.util.List;

public interface UserService {
    User getUserFromJwtToken(String token) throws UserException;

    User GetCurrentUser() throws UserException;

    User GetUserByEmail(String email) throws UserException;

    User getUserById(Long id) throws UserException;

    List<User> getAllUsers();
}
