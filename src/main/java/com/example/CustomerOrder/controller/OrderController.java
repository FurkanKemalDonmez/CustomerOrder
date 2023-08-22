package com.example.CustomerOrder.controller;


import com.example.CustomerOrder.entity.Order;
import com.example.CustomerOrder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public List<Order> findAll() {
        return orderService.findAll();
    }


    @GetMapping("/get/{orderId}")
    public Order findById(@PathVariable Integer orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping("/add/{customerId}")
    public Order addOrder(@RequestBody Order theOrder, @PathVariable Integer customerId) {

        return orderService.addOrder(theOrder, customerId);
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId){

        return orderService.deleteById(orderId);

    }

    @PostMapping("/update/{orderId}")
    public Order updateOrder(@RequestBody Order theOrder, @PathVariable Integer orderId){

        return orderService.updateOrder(theOrder, orderId);
    }


    @PostMapping("/from-date/list")
    public List<Order> fromDateList(@RequestBody LocalDate theDate){

        return orderService.fromDateList(theDate);
    }

}
