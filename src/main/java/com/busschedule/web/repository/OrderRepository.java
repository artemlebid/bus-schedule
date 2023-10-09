package com.busschedule.web.repository;

import com.busschedule.web.models.OrderForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderForm, Long> {
}
