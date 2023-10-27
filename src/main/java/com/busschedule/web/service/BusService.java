package com.busschedule.web.service;

import com.busschedule.web.models.Bus;

import java.util.List;

public interface BusService {
    List<Bus> findAllBuses();
}
