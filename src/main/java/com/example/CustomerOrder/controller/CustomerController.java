package com.example.CustomerOrder.controller;

import com.example.CustomerOrder.entity.Customer;
import com.example.CustomerOrder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public List<Customer> findAll() {
        return customerService.findAll();
    }


    @GetMapping("/get/{customerId}")
    public Customer findById(@PathVariable Integer customerId) {

        return customerService.findById(customerId);
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer theCustomer) {

        return customerService.save(theCustomer);
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable Integer customerId){

        return customerService.deleteById(customerId);

    }

    @PostMapping("/update/{customerId}")
    public Customer updateCustomer(@RequestBody Customer theCustomer, @PathVariable Integer customerId){

        return customerService.updateCustomer(theCustomer, customerId);
    }

    @GetMapping("/search/{keyword}")
    public List<Customer> searchCustomer(@PathVariable String keyword){

        return customerService.searchCustomer(keyword);
    }

    @GetMapping("without-order/list")
    public List<Customer> customersWithoutOrders(){

        return customerService.customerWithoutOrders();
    }

}
