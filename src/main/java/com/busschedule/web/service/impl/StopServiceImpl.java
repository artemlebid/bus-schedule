package com.busschedule.web.service.impl;

import com.busschedule.web.models.Stop;
import com.busschedule.web.repository.StopRepository;
import com.busschedule.web.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopServiceImpl implements StopService {
    private StopRepository stopRepository;

    @Autowired
    public StopServiceImpl(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @Override
    public List<Stop> findAllStops() {
        List<Stop> allStops = stopRepository.findAll();
        return allStops;
    }
}
