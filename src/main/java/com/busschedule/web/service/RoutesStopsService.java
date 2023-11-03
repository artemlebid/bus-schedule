package com.busschedule.web.service;

import com.busschedule.web.dto.RoutesStopsDto;
import com.busschedule.web.models.RoutesStops;

import java.time.LocalTime;
import java.util.List;

public interface RoutesStopsService {
    void save(RoutesStopsDto routesStops);
    void customSave(LocalTime arrivalTime,
                    LocalTime departureTime,
                    Double price,
                    Integer stopNumber,
                    Long stopId,
                    Long routeId);
    void customUpdate(LocalTime arrivalTime,
                      LocalTime departureTime,
                      Double price,
                      Integer stopNumber,
                      Long stopId,
                      Long routeId);
    List<RoutesStopsDto> findAllSortedRoutesStops();
    RoutesStopsDto findByRouteIdAndStopId(Long routeId,Long stopId);
}
