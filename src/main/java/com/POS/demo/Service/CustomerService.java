package com.POS.demo.Service;

import com.POS.demo.modal.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer) throws Exception;
    Customer UpdateCustomer(Long id , Customer customer) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    Customer getCustomerById(Long id) throws Exception;
    List<Customer> getAllCustomers() throws Exception;
    List<Customer> searchCustomers(String keyword)  throws Exception;

}
