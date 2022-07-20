package com.order2.service;

import com.order2.DTO.OrderDTO;
import com.order2.model.Order;
import com.order2.model.OrderItem;
import com.order2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemService orderItemService;

    @Override
    public Order getById(Integer id){
        Order order = orderRepository.findById(id);
        return order;

    }
    @Override
    public int createOrder(OrderDTO orderFromAPI){
        Integer id = orderRepository.getLastId();
        orderRepository.create(id, orderFromAPI);
        for(int i = 0; i < orderFromAPI.getOrderItems().size(); i++){
            orderItemService.createItem(orderFromAPI.getOrderItems().get(i));
        }
        return id;
    }
    @Override
    public void putOrder(Integer id, OrderDTO orderFromAPI){
        orderRepository.save(id, orderFromAPI);
        if(orderFromAPI.getOrderItems().size() == orderItemService.findItem(id).size()){
            for(int i = 0; i < orderFromAPI.getOrderItems().size(); i++){
                Integer itemId = orderFromAPI.getOrderItems().get(i).getId();
                OrderItem item = orderFromAPI.getOrderItems().get(i);
                orderItemService.saveItem(itemId, item);
            }
        }
        else if (orderFromAPI.getOrderItems().size() > orderItemService.findItem(id).size()){
            for(int i = 0; i < orderFromAPI.getOrderItems().size(); i++){
                Integer itemId = orderFromAPI.getOrderItems().get(i).getId();
                OrderItem item = orderFromAPI.getOrderItems().get(i);
                orderItemService.saveItem(itemId, item);
                if (!orderItemService.itemExists(itemId)){
                    orderItemService.createItem(item);
                }
            }

        }
        else if (orderFromAPI.getOrderItems().size() < orderItemService.findItem(id).size()){
            List<OrderItem> orderItem = orderItemService.findItem(id);
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
                        OrderItem item = orderFromAPI.getOrderItems().get(j);
                        orderItemService.saveItem(itemId, item);
                    }
                    else{
                        Integer itemId = orderItem.get(i).getId();
                        orderItemService.deleteItemById(itemId);
                    }
            }
        }
    }
    @Override
    public void deleteOrder(Integer id){
        orderItemService.deleteItem(id);
        orderRepository.delete(id);
    }

}
