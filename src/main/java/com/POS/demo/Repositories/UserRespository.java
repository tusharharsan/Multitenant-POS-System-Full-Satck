package com.POS.demo.Repositories;

import com.POS.demo.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(long id);
}
