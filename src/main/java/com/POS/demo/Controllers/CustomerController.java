package com.POS.demo.Controllers;

import com.POS.demo.PayLoad.Response.ApiResponse;
import com.POS.demo.Service.CustomerService;
import com.POS.demo.modal.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Customer> create(@RequestBody Customer  customer) throws Exception {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer  customer,
    @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(customerService.UpdateCustomer(id,customer));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception {
        customerService.deleteCustomer(id);
        ApiResponse  apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAll() throws Exception {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> search(@RequestParam String q ) throws Exception {
        return ResponseEntity.ok(customerService.searchCustomers(q));
    }

    //video completed till  8:00


}
