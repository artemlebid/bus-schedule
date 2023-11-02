package com.busschedule.web.service;

import com.busschedule.web.models.Stop;

import java.util.List;

public interface StopService {
    void saveStop(Stop stop);
    List<Stop> findAllStops();
    Stop findStopById(Long id);
}
