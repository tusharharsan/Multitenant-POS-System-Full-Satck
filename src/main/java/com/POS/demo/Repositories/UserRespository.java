package com.POS.demo.Repositories;

import com.POS.demo.modal.Store;
import com.POS.demo.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRespository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(long id);

    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}
