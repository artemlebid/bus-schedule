package com.busschedule.web.service;

import com.busschedule.web.dto.BusDto;
import com.busschedule.web.models.Bus;

import java.util.List;

public interface BusService {
    void saveBus(BusDto bus);
    void deleteBus(Long id);
    List<BusDto> findAllBuses();
    BusDto findBusById(Long id);
}
