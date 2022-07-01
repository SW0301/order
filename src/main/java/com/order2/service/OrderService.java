package com.order2.service;

import com.order2.DTO.OrderDTO;
import com.order2.model.Order;
import com.order2.model.OrderItem;
import com.order2.repository.OrderItemRepository;
import com.order2.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    private OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository){
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }



    public Order getById(Integer id){
        Order order = orderRepository.findById(id);
        order.setOrderItems(orderItemRepository.findItem(id));
        return order;

    }

    public int createOrder(OrderDTO orderFromAPI){
        Integer id = orderRepository.getLastId();
        orderRepository.create(id, orderFromAPI);
        for(int i = 0; i < orderFromAPI.getOrderItems().size(); i++){
            Integer seqId = orderItemRepository.getLastItemId();
            String item = orderFromAPI.getOrderItems().get(i).getItem();
            orderItemRepository.createItem(seqId, id, item);
        }
        return id;
    }

    public void putOrder(Integer id, OrderDTO orderFromAPI){
        orderRepository.save(id, orderFromAPI);
        if(orderFromAPI.getOrderItems().size() == orderItemRepository.findItem(id).size()){
            for(int i = 0; i < orderFromAPI.getOrderItems().size(); i++){
                Integer itemId = orderFromAPI.getOrderItems().get(i).getId();
                String item = orderFromAPI.getOrderItems().get(i).getItem();
                orderItemRepository.saveItem(itemId, item);
            }
        }
        else if (orderFromAPI.getOrderItems().size() > orderItemRepository.findItem(id).size()){
            for(int i = 0; i < orderFromAPI.getOrderItems().size(); i++){
                Integer itemId = orderFromAPI.getOrderItems().get(i).getId();
                String item = orderFromAPI.getOrderItems().get(i).getItem();
                orderItemRepository.saveItem(itemId, item);
                if (!orderItemRepository.itemExists(itemId)){
                    Integer itemSeqId = orderItemRepository.getLastItemId();
                    orderItemRepository.createItem(itemSeqId, id, item);
                }
            }

        }
        else if (orderFromAPI.getOrderItems().size() < orderItemRepository.findItem(id).size()){
            List<OrderItem> orderItem = orderItemRepository.findItem(id);
            boolean[] existsInRequest = new boolean[orderItem.size()];
            for (int i = 0; i < orderItem.size(); i++) {
                for (int j = 0; j < orderFromAPI.getOrderItems().size(); j++) {
                    if (orderFromAPI.getOrderItems().get(j).getId() == orderItem.get(i).getId()) {
                        existsInRequest[i] = true;
                        break;
                    }
                }
            }
            for(int i=0; i<existsInRequest.length;i++ ){
                for(int j=0; j<orderFromAPI.getOrderItems().size();j++)
                    if(existsInRequest[i]){
                        Integer itemId = orderFromAPI.getOrderItems().get(j).getId();
                        String item = orderFromAPI.getOrderItems().get(j).getItem();
                        orderItemRepository.saveItem(itemId, item);
                    }
                    else{
                        Integer itemId = orderItem.get(i).getId();
                        orderItemRepository.deleteItemById(itemId);
                    }
            }
        }
    }

    public void deleteOrder(Integer id){
        orderItemRepository.deleteItem(id);
        orderRepository.delete(id);
    }

}
