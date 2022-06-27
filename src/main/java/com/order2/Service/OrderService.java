package com.order2.Service;

import com.order2.DTO.OrderDTO;
import com.order2.model.Order;
import com.order2.model.OrderItem;
import com.order2.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order getById(Integer id){
        Order order = orderRepository.findById(id);
        order.setOrderItem(orderRepository.findItem(id));

        return order;

    }

    public int createOrder(OrderDTO orderFromAPI){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus(orderFromAPI.getOrderStatus());
        orderDTO.setName(orderFromAPI.getName());
        orderDTO.setPhone(orderFromAPI.getPhone());
        orderDTO.setComment(orderFromAPI.getComment());
        Integer id = orderRepository.getLastId();
        orderRepository.create(id, orderDTO);
        Order order = orderRepository.findById(id);
        for(int i=0; i < orderFromAPI.getOrderItem().size(); i++){
            Integer seqId = orderRepository.getLastSeqId();
            String item = orderFromAPI.getOrderItem().get(i).getItem();
            orderRepository.createItem(seqId, id, item);
        }
        return id;
    }

    public void putOrder(Integer id, OrderDTO orderFromAPI){
        orderRepository.save(id, orderFromAPI);
        for(int i=0; i < orderFromAPI.getOrderItem().size(); i++){
            Integer itemId = orderFromAPI.getOrderItem().get(i).getId();
            String item = orderFromAPI.getOrderItem().get(i).getItem();
            orderRepository.saveItem(itemId, item);
        }
    }

    public void deleteOrder(Integer id){
        orderRepository.alterSeq(id);
        orderRepository.delete(id);
    }

}
