package com.busschedule.web.service;

import com.busschedule.web.dto.BusDto;
import com.busschedule.web.models.Bus;

import java.util.List;

public interface BusService {
    void saveBus(BusDto bus);
    List<BusDto> findAllBuses();
    BusDto findBusById(Long id);
}
