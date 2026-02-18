package com.POS.demo.Repositories;

import com.POS.demo.modal.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer ,Long> {
    List<Customer> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String keywor,String  email);
}
