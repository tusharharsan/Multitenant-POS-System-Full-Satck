package com.POS.demo.mapper;

import com.POS.demo.PayLoad.Dto.UserDto;
import com.POS.demo.modal.User;

public class UserMapper {
    public static UserDto toDto(User saveduser) {
        UserDto userDto = new UserDto();
        userDto.setId(saveduser.getId());
        userDto.setUsername(saveduser.getUsername());
        userDto.setEmail(saveduser.getEmail());
        userDto.setPhone(saveduser.getPhone());
        userDto.setRole(saveduser.getRole());
        userDto.setCreatedAt(saveduser.getCreatedAt());
        userDto.setUpdatedAt(saveduser.getUpdatedAt());
        userDto.setLastLogin(saveduser.getLastLogin());
        return userDto;
    }
}
