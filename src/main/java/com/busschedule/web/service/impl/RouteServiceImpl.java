package com.busschedule.web.service.impl;

import com.busschedule.web.models.Route;
import com.busschedule.web.repository.RouteRepository;
import com.busschedule.web.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Route> findAllRoutes() {
        List<Route> allRoutes = routeRepository.findAll();
        return allRoutes;
    }
}
