package com.busschedule.web.repository;

import com.busschedule.web.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
