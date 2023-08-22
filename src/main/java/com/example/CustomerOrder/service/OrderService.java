package com.example.CustomerOrder.service;


import com.example.CustomerOrder.entity.Customer;
import com.example.CustomerOrder.entity.Order;
import com.example.CustomerOrder.repository.CustomerRepository;
import com.example.CustomerOrder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order theOrder, Integer customerId) {

        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {
            theOrder.setCustomer(customer);
            Order addedOrder = orderRepository.save(theOrder);
            customer.getOrders().add(addedOrder);
            customerRepository.save(customer);

            return addedOrder;
        }
        return null;
    }

    public String deleteById(Integer orderId) {

        Order order = findById(orderId);

        if (order != null){
            orderRepository.deleteById(orderId);

            return orderId + " numaralı sipariş silindi.";
        }

        return "Bu numaraya sahip sipariş yok.";

    }

    public Order findById(Integer orderId) {

        return orderRepository.findById(orderId).orElse(null);
    }

    public Order updateOrder(Order theOrder, Integer orderId) {

        Order order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            order.setCreateDate(theOrder.getCreateDate());
            order.setTotalPrice(theOrder.getTotalPrice());
            return orderRepository.save(order);
        }

        return null;
    }

    public List<Order> fromDateList(LocalDate theDate) {

        List<Order> orderList = findAll();
        List<Order> newOrderList = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getCreateDate().isAfter(theDate)) {
                newOrderList.add(order);
            }
        }

        return newOrderList;
    }
}
