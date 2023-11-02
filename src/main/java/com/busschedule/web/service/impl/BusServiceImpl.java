package com.busschedule.web.service.impl;

import com.busschedule.web.models.Bus;
import com.busschedule.web.repository.BusRepository;
import com.busschedule.web.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {
    private BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public void saveBus(Bus bus) {
        busRepository.save(bus);
    }

    @Override
    public List<Bus> findAllBuses() {
        List<Bus> allBuses = busRepository.findAll();
        return allBuses;
    }

    @Override
    public Bus findBusById(Long id) {
        Bus bus = new Bus();
        Optional<Bus> optionalBus = busRepository.findById(id);

        if(optionalBus.isPresent()){
            bus = optionalBus.get();
        }

        return bus;
    }
}
