package com.order2.controller;

import com.order2.DTO.OrderDTO;
import com.order2.service.OrderService;
import com.order2.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Api(value="Получение, создание, изменение и удаление заказа")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Возвращает заказ по id")
    public Order getOrder(@PathVariable Integer id){
        return orderService.getById(id);
    }

    @PostMapping
    @ApiOperation(value = "Creates a new order with parameters are contained in the request body")
    public Order postOrder(@RequestBody OrderDTO order){ int id = orderService.createOrder(order);
    return orderService.getById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Request for editing the Order by id")
    public void putOrder(@PathVariable Integer id, @RequestBody OrderDTO orderDTO){orderService.putOrder(id, orderDTO);}

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Removes order by id")
    public void deleteOrder(@PathVariable Integer id){orderService.deleteOrder(id);}
}
