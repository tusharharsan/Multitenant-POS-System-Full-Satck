package com.POS.demo.Service.Impl;

import com.POS.demo.Repositories.CustomerRepository;
import com.POS.demo.Service.CustomerService;
import com.POS.demo.modal.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository  customerRepository;

    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    public Customer UpdateCustomer(Long id, Customer customer) throws Exception {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                ()-> new Exception("customer not exist...")
        );
        customerToUpdate.setFullName(customer.getFullName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setPhone(customer.getPhone());
        customerToUpdate.setUpdatedAt(customer.getUpdatedAt());
        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer deletecustomer = customerRepository.findById(id).orElseThrow(
                ()-> new Exception("customer not exist...")
        );

        customerRepository.delete(deletecustomer);
    }

    @Override
    public Customer getCustomerById(Long id) throws Exception {
        return  customerRepository.findById(id).orElseThrow(
                ()-> new Exception("customer not exist...")
        );
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) throws Exception {
        return  customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword, keyword
        );
    }
}
