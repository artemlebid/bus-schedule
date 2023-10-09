package com.busschedule.web.service.impl;

import com.busschedule.web.models.OrderForm;
import com.busschedule.web.repository.OrderRepository;
import com.busschedule.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(OrderForm orderForm) {
        repository.save(orderForm);
    }
}
