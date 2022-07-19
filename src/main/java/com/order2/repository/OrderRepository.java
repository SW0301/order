package com.order2.repository;

import com.order2.DTO.OrderDTO;
import com.order2.model.Order;

import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository {


    Order findById(Integer id);

    void create( Integer id, OrderDTO order);

    void save( Integer id, OrderDTO order);

    void delete( Integer id);

    Integer getLastId();

}
