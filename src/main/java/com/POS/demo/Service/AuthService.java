package com.POS.demo.Service;

import com.POS.demo.Exceptions.UserException;
import com.POS.demo.PayLoad.Dto.UserDto;
import com.POS.demo.PayLoad.Response.AuthResponse;

public interface AuthService {
    AuthResponse register(UserDto userDto) throws Exception, UserException;
    AuthResponse login(UserDto userDto) throws UserException;

}
