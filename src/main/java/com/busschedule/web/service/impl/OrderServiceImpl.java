package com.busschedule.web.service.impl;

import com.busschedule.web.models.OrderForm;
import com.busschedule.web.repository.OrderRepository;
import com.busschedule.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderForm> findAllOrders() {
        List<OrderForm> allOrders = repository.findAll();
        return allOrders;
    }

    @Override
    public List<OrderForm> findAllSortedOrders() {
        List<OrderForm> allSortedOrders = repository.sortedOrders();
        return allSortedOrders;
    }

    @Override
    public void save(OrderForm orderForm) {
        repository.save(orderForm);
    }
}
