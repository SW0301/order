package com.order2.service;


import com.order2.model.OrderItem;
import com.order2.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findItem(Integer id){ return orderItemRepository.findItem(id);}

    @Override
    public void createItem(OrderItem orderItem){orderItemRepository.createItem(orderItem);}

    @Override
    public void saveItem( Integer id, OrderItem orderItem){orderItemRepository.saveItem(id, orderItem);}

    @Override
    public void deleteItem(Integer orderId){orderItemRepository.deleteItem(orderId);}

    @Override
    public boolean itemExists(Integer id){return orderItemRepository.itemExists(id);}

    @Override
    public void deleteItemById(Integer id){orderItemRepository.deleteItemById(id);}
}
