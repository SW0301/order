package com.order2.mappers;

import com.order2.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    List<OrderItem> findItem(@Param("id") Integer id);

    void createItem(@Param("oi") OrderItem orderItem);

    void saveItem(@Param("id") Integer id, @Param("oi") OrderItem orderItem);

    void deleteItem(@Param("id") Integer orderId);


    boolean itemExists(@Param("id") Integer id);

    void deleteItemById(@Param("id")Integer id);
}
