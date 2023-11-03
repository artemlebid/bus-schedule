package com.busschedule.web.service;

import com.busschedule.web.dto.ScheduleDto;
import com.busschedule.web.models.Schedule;

import java.util.List;

public interface ScheduleService {
    ScheduleDto findScheduleById(Long id);
    void saveSchedule(ScheduleDto schedule);
    void saveScheduleList(List<ScheduleDto> schedules);
    List<ScheduleDto> findScheduleToday();
    List<ScheduleDto> findScheduleTomorrow();
    List<ScheduleDto> findScheduleAfterTomorrow();
    List<ScheduleDto> findOverdueSchedule();
    List<ScheduleDto> findAllByStopsAndTimeForSearchToday(String stopName);
    List<ScheduleDto> findAllByStopsAndTimeForSearchTomorrow(String stopName);
    List<ScheduleDto> findAllByStopsAndTimeForSearchAfterTomorrow(String stopName);
}
