package com.busschedule.web.service;

import com.busschedule.web.dto.OrderFormDto;
import com.busschedule.web.models.OrderForm;

import java.util.List;

public interface OrderService {
    List<OrderFormDto> findAllOrders();
    List<OrderFormDto> findAllSortedOrders();
    void save(OrderFormDto orderForm);
}
