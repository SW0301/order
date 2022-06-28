package com.order2.service;

import com.order2.DTO.OrderDTO;
import com.order2.model.Order;
import com.order2.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order getById(Integer id){
        Order order = orderRepository.findById(id);
        order.setOrderItems(orderRepository.findItem(id));

        return order;

    }

    public int createOrder(OrderDTO orderFromAPI){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus(orderFromAPI.getOrderStatusId());
        orderDTO.setCustomerName(orderFromAPI.getCustomerName());
        orderDTO.setCustomerPhone(orderFromAPI.getCustomerPhone());
        orderDTO.setCustomerComment(orderFromAPI.getCustomerComment());
        Integer id = orderRepository.getLastId();
        orderRepository.create(id, orderDTO);
        Order order = orderRepository.findById(id);
        for(int i = 0; i < orderFromAPI.getOrderItems().size(); i++){
            Integer seqId = orderRepository.getLastSeqId();
            String item = orderFromAPI.getOrderItems().get(i).getItem();
            orderRepository.createItem(seqId, id, item);
        }
        return id;
    }

    public void putOrder(Integer id, OrderDTO orderFromAPI){
        orderRepository.save(id, orderFromAPI);
        for(int i = 0; i < orderFromAPI.getOrderItems().size(); i++){
            Integer itemId = orderFromAPI.getOrderItems().get(i).getId();
            String item = orderFromAPI.getOrderItems().get(i).getItem();
            orderRepository.saveItem(itemId, item);
        }
    }

    public void deleteOrder(Integer id){
        Integer itemId = orderRepository.getLastSeqId();
        orderRepository.alterItemSeq(itemId);
        orderRepository.deleteItem(id);
        orderRepository.alterSeq(id);
        orderRepository.delete(id);
    }

}
