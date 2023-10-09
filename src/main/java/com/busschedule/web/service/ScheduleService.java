package com.busschedule.web.service;

import com.busschedule.web.models.Schedule;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Schedule findScheduleById(Long id);
    void saveSchedule(List<Schedule> schedules);
    List<Schedule> findScheduleToday();
    List<Schedule> findScheduleTomorrow();
    List<Schedule> findScheduleAfterTomorrow();
    List<Schedule> findOverdueSchedule();
    List<Schedule> findAllByStopsAndTimeForSearchToday(String stopName);
    List<Schedule> findAllByStopsAndTimeForSearchTomorrow(String stopName);
    List<Schedule> findAllByStopsAndTimeForSearchAfterTomorrow(String stopName);
}
