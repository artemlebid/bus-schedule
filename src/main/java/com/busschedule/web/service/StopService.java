package com.busschedule.web.service;

import com.busschedule.web.models.Stop;

import java.util.List;

public interface StopService {
    List<Stop> findAllStops();
}
