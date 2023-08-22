package com.example.CustomerOrder.service;



import com.example.CustomerOrder.entity.Customer;
import com.example.CustomerOrder.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer theCustomer) {
        return customerRepository.save(theCustomer);
    }

    public String deleteById(Integer customerId) {

        Customer customer = findById(customerId);

        if (customer != null){

            customerRepository.deleteById(customerId);

            return customerId + " numaralı müşteri silindi.";
        }

        return "Bu numaraya sahip müşteri yok.";

    }

    public Customer findById(Integer customerId) {

        return customerRepository.findById(customerId).orElse(null);

    }

    public List<Customer> searchCustomer(String keyword) {

        List<Customer> customerList = findAll();
        List<Customer> searchedCustomerList = new ArrayList<>();

        for (Customer customer : customerList) {
            if (customer.getName().contains(keyword)) {
                searchedCustomerList.add(customer);
            }
        }

        return searchedCustomerList;
    }

    public List<Customer> customerWithoutOrders() {

        List<Customer> customerList = findAll();
        List<Customer> customerWithoutOrdersList = new ArrayList<>();

        for (Customer customer : customerList) {

            if (customer.getOrders().isEmpty()) {
                customerWithoutOrdersList.add(customer);
            }
        }

        return customerWithoutOrdersList;
    }

    public Customer updateCustomer(Customer theCustomer, Integer customerId) {

        Customer customer = findById(customerId);

        if (customer != null){
            customer.setName(theCustomer.getName());
            customer.setAge(theCustomer.getAge());
            return customerRepository.save(customer);
        }
        return null;
    }
}
