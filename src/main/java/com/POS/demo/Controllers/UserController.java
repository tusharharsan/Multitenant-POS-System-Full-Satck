package com.POS.demo.Controllers;

import com.POS.demo.Exceptions.UserException;
import com.POS.demo.PayLoad.Dto.UserDto;
import com.POS.demo.Service.UserService;
import com.POS.demo.mapper.UserMapper;
import com.POS.demo.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String token) throws UserException {
        User user = userService.getUserFromJwtToken(token);
        return ResponseEntity.ok(UserMapper.toDto(user));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws UserException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));

    }



}
