package com.order2.repository;

import com.order2.mappers.OrderItemMapper;
import com.order2.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public List<OrderItem> findItem(Integer id){ return orderItemMapper.findItem(id);}
    @Override
    public void createItem(OrderItem orderItem){orderItemMapper.createItem(orderItem);}
    @Override
    public void saveItem( Integer id, OrderItem orderItem){orderItemMapper.saveItem(id, orderItem);}
    @Override
    public void deleteItem(Integer orderId){orderItemMapper.deleteItem(orderId);}
    @Override
    public boolean itemExists(Integer id){return orderItemMapper.itemExists(id);}
    @Override
    public void deleteItemById(Integer id){orderItemMapper.deleteItemById(id);}
}
