package com.busschedule.web.repository;

import com.busschedule.web.models.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopRepository extends JpaRepository<Stop, Long> {
}
