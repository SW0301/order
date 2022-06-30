package com.order2.repository;

import com.order2.model.OrderItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderItemRepository {
    @Select("SELECT oi.id, oi.order_id as orderId, oi.item_name as itemName FROM \"order\" left join order_item oi on \"order\".id = oi.order_id WHERE oi.order_id = #{id}")
    List<OrderItem> findItem(Integer id);

    @Insert("INSERT INTO order_item VALUES (#{id}, ${orderId}, \'${itemName}\')")
    void createItem(Integer id, Integer orderId, String itemName);

    @Update("UPDATE order_item SET item_name = \'${itemName}\' WHERE id=${id}")
    void saveItem(Integer id, String itemName);

    @Delete("DELETE FROM order_item where order_id=#{orderId}")
    void deleteItem(Integer orderId);

    @Select("SELECT nextval('order_item_seq')")
    Integer getLastItemId();

}
