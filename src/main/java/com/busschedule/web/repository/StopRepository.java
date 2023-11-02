package com.busschedule.web.repository;

import com.busschedule.web.models.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StopRepository extends JpaRepository<Stop, Long> {
    Optional<Stop> findById(Long id);
}
