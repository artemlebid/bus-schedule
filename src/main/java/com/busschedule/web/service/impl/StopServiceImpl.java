package com.busschedule.web.service.impl;

import com.busschedule.web.dto.StopDto;
import com.busschedule.web.models.Stop;
import com.busschedule.web.repository.StopRepository;
import com.busschedule.web.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StopServiceImpl implements StopService {
    private StopRepository stopRepository;

    @Autowired
    public StopServiceImpl(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @Override
    public void saveStop(StopDto stop) {
        stopRepository.save(mapToStop(stop));
    }

    @Override
    public void deleteStop(Long id) {
        stopRepository.deleteById(id);
    }

    @Override
    public List<StopDto> findAllStops() {
        List<Stop> allStops = stopRepository.findAll();
        return allStops.stream().map(stop -> mapToStopDto(stop)).collect(Collectors.toList());
    }

    @Override
    public StopDto findStopById(Long id) {
        Stop stop = new Stop();
        Optional<Stop> optionalStop = stopRepository.findById(id);

        if(optionalStop.isPresent()){
            stop = optionalStop.get();
        }

        return mapToStopDto(stop);
    }

    private StopDto mapToStopDto(Stop stop){
        StopDto stopDto = StopDto.builder()
                .id(stop.getId())
                .name(stop.getName())
                .build();
        return stopDto;
    }

    private Stop mapToStop(StopDto stopDto){
        Stop stop = Stop.builder()
                .id(stopDto.getId())
                .name(stopDto.getName())
                .build();
        return stop;
    }
}
