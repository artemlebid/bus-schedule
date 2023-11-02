package com.busschedule.web.service;

import com.busschedule.web.models.RoutesStops;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface RoutesStopsService {
    void save(RoutesStops routesStops);
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
    List<RoutesStops> findAllSortedRoutesStops();
    RoutesStops findByRouteIdAndStopId(Long routeId,Long stopId);
}
