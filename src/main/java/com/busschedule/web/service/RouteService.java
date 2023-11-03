package com.busschedule.web.service;

import com.busschedule.web.dto.RouteDto;
import com.busschedule.web.models.Route;

import java.util.List;

public interface RouteService {
    void saveRoute(RouteDto route);
    List<RouteDto> findAllRoutes();
    RouteDto findRouteById(Long id);
}
