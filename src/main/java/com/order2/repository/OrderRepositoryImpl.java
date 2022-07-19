package com.order2.repository;

import com.order2.DTO.OrderDTO;
import com.order2.mappers.OrderMapper;
import com.order2.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private OrderMapper orderMapper;
    @Autowired
    public OrderRepositoryImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.findById(id);
    }

    @Override
    public void create(Integer id, OrderDTO order) {
        orderMapper.create(id, order);
    }

    @Override
    public void save(Integer id, OrderDTO order) {
        orderMapper.save(id,order);
    }

    @Override
    public void delete(Integer id) {
        orderMapper.delete(id);
    }

    @Override
    public Integer getLastId() {
        return orderMapper.getLastId();
    }
}
