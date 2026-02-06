package com.POS.demo.Service.Impl;

import com.POS.demo.Exceptions.UserException;
import com.POS.demo.Repositories.UserRespository;
import com.POS.demo.Service.UserService;
import com.POS.demo.config.JwtProvider;
import com.POS.demo.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRespository userRespository;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromjwt(token);
        User user = userRespository.findByEmail(email);
        if(user==null){
            throw new UserException("invalid Token");
        }
        return user;
    }

    @Override
    public User GetCurrentUser() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRespository.findByEmail(email);
        if(user==null){
            throw new UserException("user not found");
        }
        return user;
    }

    @Override
    public User GetUserByEmail(String email) throws UserException {
        User user = userRespository.findByEmail(email);
        if(user!=null){
            throw new UserException("invalid Token");
        }
        return user;

    }

    @Override
    public User getUserById(Long id) throws UserException {
        User user = userRespository.findById((long)id);
        if(user==null){
            throw new UserException("user not found");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRespository.findAll();
    }
}
