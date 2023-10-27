package com.busschedule.web.service.impl;

import com.busschedule.web.models.RoutesStops;
import com.busschedule.web.repository.RoutesStopsRepository;
import com.busschedule.web.service.RoutesStopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutesStopsServiceImpl implements RoutesStopsService {
    private RoutesStopsRepository routesStopsRepository;

    @Autowired
    public RoutesStopsServiceImpl(RoutesStopsRepository routesStopsRepository) {
        this.routesStopsRepository = routesStopsRepository;
    }

    @Override
    public List<RoutesStops> findAllSortedRoutesStops() {
        List<RoutesStops> allSortedRoutesStops = routesStopsRepository.findAllSorted();
        return allSortedRoutesStops;
    }

    @Override
    public List<RoutesStops> findAllRoutesStops() {
        List<RoutesStops> allRouteStops = routesStopsRepository.findAll();
        return allRouteStops;
    }
}
