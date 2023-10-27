package com.busschedule.web.service;

import com.busschedule.web.models.OrderForm;

import java.util.List;

public interface OrderService {
    List<OrderForm> findAllOrders();
    List<OrderForm> findAllSortedOrders();
    void save(OrderForm orderForm);
}
