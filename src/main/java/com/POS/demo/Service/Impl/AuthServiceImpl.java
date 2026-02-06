package com.POS.demo.Service.Impl;

import com.POS.demo.Domain.UserRole;
import com.POS.demo.Exceptions.UserException;
import com.POS.demo.PayLoad.Dto.UserDto;
import com.POS.demo.PayLoad.Response.AuthResponse;
import com.POS.demo.Repositories.UserRespository;
import com.POS.demo.Service.AuthService;
import com.POS.demo.config.JwtProvider;
import com.POS.demo.mapper.UserMapper;
import com.POS.demo.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRespository userRespository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    private final CustomUserServiceImpl customUserService;

    @Override
    public AuthResponse register(UserDto userDto) throws UserException {
        User user = userRespository.findByEmail(userDto.getEmail());
        if(user!=null){
            throw new UserException("email id already exists");
        }
        if(userDto.getRole().equals(UserRole.ROLE_ADMIN))
            throw new UserException("cannot register as admin");{
        }

        User newuser = new User();
        newuser.setEmail(userDto.getEmail());
        newuser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newuser.setUsername(userDto.getUsername());
        if(userDto.getRole() != null) {
            newuser.setRole(userDto.getRole());
        } else {
            newuser.setRole(UserRole.ROLE_USER);
        }
        newuser.setPhone(userDto.getPhone());
        newuser.setLastLogin(LocalDateTime.now());
        newuser.setCreatedAt(LocalDateTime.now());
        newuser.setUpdatedAt(LocalDateTime.now());

        User saveduser = userRespository.save(newuser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail() , userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registeration Successful");
        authResponse.setUser(UserMapper.toDto(saveduser));

        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserException {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        Authentication authentication = authenticate(email , password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role = authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateJwtToken(authentication);
        User user = userRespository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRespository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successful");
        authResponse.setUser(UserMapper.toDto(user));
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {
        UserDetails userDetails = customUserService.loadUserByUsername(email);
        if(userDetails==null){
            throw new UserException("email id doesn't exist "+email);
        }
        if(!passwordEncoder.matches(password , userDetails.getPassword())){
            throw new UserException("invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
    }
}
