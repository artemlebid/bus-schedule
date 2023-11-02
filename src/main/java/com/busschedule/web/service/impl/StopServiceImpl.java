package com.busschedule.web.service.impl;

import com.busschedule.web.models.Stop;
import com.busschedule.web.repository.StopRepository;
import com.busschedule.web.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopServiceImpl implements StopService {
    private StopRepository stopRepository;

    @Autowired
    public StopServiceImpl(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @Override
    public void saveStop(Stop stop) {
        stopRepository.save(stop);
    }

    @Override
    public List<Stop> findAllStops() {
        List<Stop> allStops = stopRepository.findAll();
        return allStops;
    }

    @Override
    public Stop findStopById(Long id) {
        Stop stop = new Stop();
        Optional<Stop> optionalStop = stopRepository.findById(id);

        if(optionalStop.isPresent()){
            stop = optionalStop.get();
        }

        return stop;
    }
}
