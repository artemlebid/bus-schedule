package com.busschedule.web.service;

import com.busschedule.web.models.Route;

import java.util.List;

public interface RouteService {
    void saveRoute(Route route);
    List<Route> findAllRoutes();
    Route findRouteById(Long id);
}
