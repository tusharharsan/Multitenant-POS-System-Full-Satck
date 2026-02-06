package com.POS.demo.Service.Impl;

import com.POS.demo.Repositories.UserRespository;
import com.POS.demo.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRespository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(
                user.getRole().toString()
        );
        Collection<GrantedAuthority> authorities = Collections.singleton(authority);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );

    }
}
