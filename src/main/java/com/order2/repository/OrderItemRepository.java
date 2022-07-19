package com.order2.repository;

import com.order2.model.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemRepository {

    List<OrderItem> findItem(Integer id);

    void createItem(OrderItem orderItem);

    void saveItem( Integer id, OrderItem orderItem);

    void deleteItem(Integer orderId);


    boolean itemExists(Integer id);

    void deleteItemById(Integer id);
}
