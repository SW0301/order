package com.order2.DTO;

import com.order2.model.OrderItem;

import java.util.List;

public class OrderDTO {
    private short orderStatusId;
    private String customerName;
    private String customerPhone;
    private String customerComment;

    private List<OrderItem> orderItems;

    public short getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatus(short orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerComment() {
        return customerComment;
    }

    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
