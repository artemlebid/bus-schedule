package com.busschedule.web.service.impl;

import com.busschedule.web.dto.OrderFormDto;
import com.busschedule.web.models.OrderForm;
import com.busschedule.web.repository.OrderRepository;
import com.busschedule.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderFormDto> findAllOrders() {
        List<OrderForm> allOrders = repository.findAll();
        return allOrders.stream().map(orderForm -> mapToOrderFormDto(orderForm)).collect(Collectors.toList());
    }

    @Override
    public List<OrderFormDto> findAllSortedOrders() {
        List<OrderForm> allSortedOrders = repository.sortedOrders();
        return allSortedOrders.stream().map(orderForm -> mapToOrderFormDto(orderForm)).collect(Collectors.toList());
    }

    @Override
    public void saveOrder(OrderFormDto orderForm) {
        repository.save(mapToOrderForm(orderForm));
    }

    @Override
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }

    private OrderFormDto mapToOrderFormDto(OrderForm orderForm){
        OrderFormDto orderFormDto = OrderFormDto.builder()
                .id(orderForm.getId())
                .payment(orderForm.getPayment())
                .countSeats(orderForm.getCountSeats())
                .email(orderForm.getEmail())
                .fromStop(orderForm.getFromStop())
                .name(orderForm.getName())
                .phone(orderForm.getPhone())
                .surname(orderForm.getSurname())
                .toStop(orderForm.getToStop())
                .schedule(orderForm.getSchedule())
                .created(orderForm.getCreated())
                .build();
        return orderFormDto;
    }

    private OrderForm mapToOrderForm(OrderFormDto orderFormDto){
        OrderForm orderForm = OrderForm.builder()
                .id(orderFormDto.getId())
                .payment(orderFormDto.getPayment())
                .countSeats(orderFormDto.getCountSeats())
                .email(orderFormDto.getEmail())
                .fromStop(orderFormDto.getFromStop())
                .name(orderFormDto.getName())
                .phone(orderFormDto.getPhone())
                .surname(orderFormDto.getSurname())
                .toStop(orderFormDto.getToStop())
                .schedule(orderFormDto.getSchedule())
                .created(orderFormDto.getCreated())
                .build();
        return orderForm;
    }
}
