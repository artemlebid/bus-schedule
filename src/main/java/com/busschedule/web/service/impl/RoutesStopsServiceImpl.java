package com.busschedule.web.service.impl;

import com.busschedule.web.models.RoutesStops;
import com.busschedule.web.repository.RoutesStopsRepository;
import com.busschedule.web.service.RoutesStopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class RoutesStopsServiceImpl implements RoutesStopsService {
    private RoutesStopsRepository routesStopsRepository;

    @Autowired
    public RoutesStopsServiceImpl(RoutesStopsRepository routesStopsRepository) {
        this.routesStopsRepository = routesStopsRepository;
    }

    @Override
    public void save(RoutesStops routesStops) {
        routesStopsRepository.save(routesStops);
    }

    @Override
    public void customSave(LocalTime arrivalTime, LocalTime departureTime, Double price, Integer stopNumber, Long stopId, Long routeId) {
        RoutesStops routesStops = routesStopsRepository.findByRouteIdAndStopId(routeId, stopId);
        if(routesStops != null){
            routesStopsRepository.customUpdate(arrivalTime, departureTime, price, stopNumber, stopId, routeId);
        }
        else{
            routesStopsRepository.customSave(arrivalTime, departureTime, price, stopNumber, stopId, routeId);
        }
    }

    @Override
    public void customUpdate(LocalTime arrivalTime, LocalTime departureTime, Double price, Integer stopNumber, Long stopId, Long routeId) {
        routesStopsRepository.customUpdate(arrivalTime, departureTime, price, stopNumber, stopId, routeId);
    }

    @Override
    public List<RoutesStops> findAllSortedRoutesStops() {
        List<RoutesStops> allSortedRoutesStops = routesStopsRepository.findAllSorted();
        return allSortedRoutesStops;
    }

    @Override
    public RoutesStops findByRouteIdAndStopId(Long routeId, Long stopId) {
        RoutesStops routesStops = routesStopsRepository.findByRouteIdAndStopId(routeId, stopId);
        return routesStops;
    }
}
