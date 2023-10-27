package com.busschedule.web.repository;

import com.busschedule.web.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {

}
