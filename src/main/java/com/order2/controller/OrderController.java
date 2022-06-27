package com.order2.controller;

import com.order2.DTO.OrderDTO;
import com.order2.Service.OrderService;
import com.order2.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable Integer id){
        return orderService.getById(id);
    }

    @PostMapping("/order")
    public Order postOrder(@RequestBody OrderDTO order){ int id = orderService.createOrder(order);
    return orderService.getById(id);
    }

    @PutMapping("/order/{id}")
    public void putOrder(@PathVariable Integer id, @RequestBody OrderDTO orderDTO){orderService.putOrder(id, orderDTO);}

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Integer id){orderService.deleteOrder(id);}
}
