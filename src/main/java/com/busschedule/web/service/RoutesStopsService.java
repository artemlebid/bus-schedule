package com.busschedule.web.service;

import com.busschedule.web.models.RoutesStops;

import java.util.List;

public interface RoutesStopsService {
    List<RoutesStops> findAllSortedRoutesStops();
    List<RoutesStops> findAllRoutesStops();
}
