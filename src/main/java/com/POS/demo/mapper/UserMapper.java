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
        userDto.setStoreId(saveduser.getStore()!=null ?   saveduser.getStore().getId():null);
        userDto.setBranchId(saveduser.getBranch()!=null  ?  saveduser.getBranch().getId():null );
        return userDto;
    }

    public static  User toEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setRole(userDto.getRole());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setLastLogin(userDto.getLastLogin());
        return user;
    }
}
