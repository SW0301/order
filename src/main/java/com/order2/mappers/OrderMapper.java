package com.order2.mappers;

import com.order2.DTO.OrderDTO;
import com.order2.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface OrderMapper {

    Order findById(@Param("id") Integer id);

    void create(@Param("id") Integer id, @Param("o") OrderDTO order);

    void save(@Param("id") Integer id, @Param("o") OrderDTO order);

    void delete(@Param("id") Integer id);

    Integer getLastId();
}
