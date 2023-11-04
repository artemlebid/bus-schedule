package com.busschedule.web.service;

import com.busschedule.web.dto.StopDto;
import com.busschedule.web.models.Stop;

import java.util.List;

public interface StopService {
    void saveStop(StopDto stop);
    void deleteStop(Long id);
    List<StopDto> findAllStops();
    StopDto findStopById(Long id);
}
