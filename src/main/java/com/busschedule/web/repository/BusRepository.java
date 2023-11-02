package com.busschedule.web.repository;

import com.busschedule.web.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findById(Long id);
}
