package com.order2.service;

import com.order2.model.OrderItem;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface OrderItemService {

    List<OrderItem> findItem(Integer id);

    void createItem(OrderItem orderItem);

    void saveItem(Integer id, OrderItem orderItem);

    void deleteItem(Integer orderId);


    boolean itemExists(Integer id);

    void deleteItemById(Integer id);
}
