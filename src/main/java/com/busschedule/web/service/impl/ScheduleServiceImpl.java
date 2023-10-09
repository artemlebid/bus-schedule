package com.busschedule.web.service.impl;

import com.busschedule.web.models.Schedule;
import com.busschedule.web.repository.ScheduleRepository;
import com.busschedule.web.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository repository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Schedule findScheduleById(Long id) {
        Schedule schedule = repository.findScheduleById(id);
        return schedule;
    }

    @Override
    public void saveSchedule(List<Schedule> schedules) {
        repository.saveAll(schedules);
    }

    @Override
    public List<Schedule> findScheduleToday() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime endOfDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleToday = repository.findScheduleByTimeInterval(currentDateTime, endOfDay);
        return scheduleToday;
    }

    @Override
    public List<Schedule> findScheduleTomorrow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleTomorrow = repository.findScheduleByTimeInterval(startOfNextDay, endOfNextDay);
        return scheduleTomorrow;
    }

    @Override
    public List<Schedule> findScheduleAfterTomorrow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(2).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleAfterTomorrow = repository.findScheduleByTimeInterval(startOfNextDay, endOfNextDay);
        return scheduleAfterTomorrow;
    }

    @Override
    public List<Schedule> findOverdueSchedule() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Schedule> overdueSchedule = repository.findOverdueSchedule(currentDateTime);
        return overdueSchedule;
    }

    @Override
    public List<Schedule> findAllByStopsAndTimeForSearchToday(String stopName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime endOfDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);

        List<Schedule> scheduleToday = repository.findAllByStopsAndTimeForSearch(stopName, currentDateTime, endOfDay);
        return scheduleToday;
    }

    @Override
    public List<Schedule> findAllByStopsAndTimeForSearchTomorrow(String stopName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleTomorrow = repository.findAllByStopsAndTimeForSearch(stopName, startOfNextDay, endOfNextDay);
        return scheduleTomorrow;
    }

    @Override
    public List<Schedule> findAllByStopsAndTimeForSearchAfterTomorrow(String stopName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(2).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(2).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleAfterTomorrow = repository.findAllByStopsAndTimeForSearch(stopName, startOfNextDay, endOfNextDay);
        return scheduleAfterTomorrow;
    }
}
