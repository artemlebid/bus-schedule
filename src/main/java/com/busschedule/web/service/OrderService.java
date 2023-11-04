package com.busschedule.web.service;

import com.busschedule.web.dto.OrderFormDto;

import java.util.List;

public interface OrderService {
    List<OrderFormDto> findAllOrders();
    List<OrderFormDto> findAllSortedOrders();
    void saveOrder(OrderFormDto orderForm);
    void deleteOrder(Long id);
}
