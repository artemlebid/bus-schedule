package com.busschedule.web.service;

import com.busschedule.web.models.Bus;

import java.util.List;

public interface BusService {
    void saveBus(Bus bus);
    List<Bus> findAllBuses();
    Bus findBusById(Long id);
}
