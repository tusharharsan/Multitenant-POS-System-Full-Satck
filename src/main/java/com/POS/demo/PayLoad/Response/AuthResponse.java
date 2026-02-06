package com.POS.demo.PayLoad.Response;

import com.POS.demo.PayLoad.Dto.UserDto;
import com.POS.demo.modal.User;
import lombok.Data;

@Data
public class AuthResponse {
    private String message;

    private String jwt;

    private UserDto user;

}
