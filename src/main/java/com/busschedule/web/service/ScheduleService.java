package com.busschedule.web.service;

import com.busschedule.web.models.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule findScheduleById(Long id);
    void saveSchedule(Schedule schedule);
    void saveScheduleList(List<Schedule> schedules);
    List<Schedule> findScheduleToday();
    List<Schedule> findScheduleTomorrow();
    List<Schedule> findScheduleAfterTomorrow();
    List<Schedule> findOverdueSchedule();
    List<Schedule> findAllByStopsAndTimeForSearchToday(String stopName);
    List<Schedule> findAllByStopsAndTimeForSearchTomorrow(String stopName);
    List<Schedule> findAllByStopsAndTimeForSearchAfterTomorrow(String stopName);
}
