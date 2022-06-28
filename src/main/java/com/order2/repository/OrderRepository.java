package com.order2.repository;

import com.order2.DTO.OrderDTO;
import com.order2.model.Order;
import com.order2.model.OrderItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface OrderRepository {

    @Select("SELECT id, order_status_id as orderStatusId, customer_name as customerName, customer_phone as customerPhone, customer_comment as customerComment FROM \"order\" WHERE id = #{id}")
    Order findById( Integer id);

    @Select("SELECT oi.id, oi.order_id as order, oi.item_name as item FROM \"order\" left join order_item oi on \"order\".id = oi.order_id WHERE oi.order_id = #{id}")
    List<OrderItem> findItem(Integer id);


    @Insert("INSERT INTO \"order\" (id, order_status_id, customer_name , customer_phone , customer_comment) VALUES ( #{id}, ${order.getOrderStatusId()}, \'${order.getCustomerName()}\', \'${order.getCustomerPhone()}\', \'${order.getCustomerComment()}\')")
    void create(Integer id, OrderDTO order);

    @Insert("INSERT INTO order_item VALUES (#{id}, ${orderId}, \'${itemName}\')")
    void createItem(Integer id, Integer orderId, String itemName);

    @Update("UPDATE \"order\" SET order_status_id=${order.getOrderStatusId()}, customer_name=\'${order.getCustomerName()}\', customer_phone=\'${order.getCustomerPhone()}\', customer_comment=\'${order.getCustomerComment()}\' WHERE id=#{id}")
    void save(Integer id, OrderDTO order);

    @Update("UPDATE order_item SET item_name = \'${itemName}\' WHERE id=${id}")
    void saveItem(Integer id, String itemName);

    @Delete("delete from \"order\" where id=#{id}")
    void delete(Integer id);

    @Delete("DELETE FROM order_item where order_id=#{id}")
    void deleteItem(Integer id);

    @Select("SELECT nextval('order_seq') ")
    Integer getLastId();

    @Select("SELECT nextval('order_item_seq')")
    Integer getLastSeqId();

    @Update("ALTER SEQUENCE order_seq START WITH ${id}")
    void alterSeq(Integer id);

    @Update("ALTER SEQUENCE order_item_seq START WITH ${id}")
    void alterItemSeq(Integer id);

}
