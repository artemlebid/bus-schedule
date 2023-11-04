package com.busschedule.web.service.impl;

import com.busschedule.web.dto.RoutesStopsDto;
import com.busschedule.web.models.RoutesStops;
import com.busschedule.web.models.RoutesStopsId;
import com.busschedule.web.repository.RoutesStopsRepository;
import com.busschedule.web.service.RoutesStopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutesStopsServiceImpl implements RoutesStopsService {
    private RoutesStopsRepository routesStopsRepository;

    @Autowired
    public RoutesStopsServiceImpl(RoutesStopsRepository routesStopsRepository) {
        this.routesStopsRepository = routesStopsRepository;
    }

    @Override
    public void save(RoutesStopsDto routesStops) {
        routesStopsRepository.save(mapToRoutesStops(routesStops));
    }

    @Override
    public void deleteRoutesStops(Long routeId, Long stopId) {
        RoutesStopsId routesStopsId = new RoutesStopsId(routeId, stopId);
        routesStopsRepository.deleteById(routesStopsId);
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
    public List<RoutesStopsDto> findAllSortedRoutesStops() {
        List<RoutesStops> allSortedRoutesStops = routesStopsRepository.findAllSorted();
        return allSortedRoutesStops.stream().map(routesStops -> mapToRoutesStopsDto(routesStops)).collect(Collectors.toList());
    }

    @Override
    public RoutesStopsDto findByRouteIdAndStopId(Long routeId, Long stopId) {
        RoutesStops routesStops = routesStopsRepository.findByRouteIdAndStopId(routeId, stopId);
        return mapToRoutesStopsDto(routesStops);
    }

    private RoutesStopsDto mapToRoutesStopsDto(RoutesStops routesStops){
        RoutesStopsDto routesStopsDto = RoutesStopsDto.builder()
                .arrivalTime(routesStops.getArrivalTime())
                .departureTime(routesStops.getDepartureTime())
                .price(routesStops.getPrice())
                .stopNumber(routesStops.getStopNumber())
                .stop(routesStops.getStop())
                .route(routesStops.getRoute())
                .build();
        return routesStopsDto;
    }

    private RoutesStops mapToRoutesStops(RoutesStopsDto routesStopsDto){
        RoutesStops routesStops = RoutesStops.builder()
                .arrivalTime(routesStopsDto.getArrivalTime())
                .departureTime(routesStopsDto.getDepartureTime())
                .price(routesStopsDto.getPrice())
                .stopNumber(routesStopsDto.getStopNumber())
                .stop(routesStopsDto.getStop())
                .route(routesStopsDto.getRoute())
                .build();
        return routesStops;
    }
}
