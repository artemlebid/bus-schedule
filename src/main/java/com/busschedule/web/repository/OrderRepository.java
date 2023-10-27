package com.busschedule.web.repository;

import com.busschedule.web.models.OrderForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderForm, Long> {
    @Query("SELECT c FROM OrderForm c ORDER BY c.id")
    List<OrderForm> sortedOrders();
}
