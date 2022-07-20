package com.order2.service;

import com.order2.DTO.OrderDTO;
import com.order2.model.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order getById(Integer id);
    int createOrder(OrderDTO orderFromAPI);

    void putOrder(Integer id, OrderDTO orderFromAPI);
    void deleteOrder(Integer id);

}
