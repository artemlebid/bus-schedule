package com.busschedule.web.service.impl;

import com.busschedule.web.models.Schedule;
import com.busschedule.web.repository.ScheduleRepository;
import com.busschedule.web.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule findScheduleById(Long id) {
        Schedule schedule = new Schedule();
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        if(optionalSchedule.isPresent()){
            schedule = optionalSchedule.get();
        }
        return schedule;
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void saveScheduleList(List<Schedule> schedules) {
        scheduleRepository.saveAll(schedules);
    }

    @Override
    public List<Schedule> findScheduleToday() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime endOfDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleToday = scheduleRepository.findScheduleByTimeInterval(currentDateTime, endOfDay);
        return scheduleToday;
    }

    @Override
    public List<Schedule> findScheduleTomorrow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleTomorrow = scheduleRepository.findScheduleByTimeInterval(startOfNextDay, endOfNextDay);
        return scheduleTomorrow;
    }

    @Override
    public List<Schedule> findScheduleAfterTomorrow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(2).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleAfterTomorrow = scheduleRepository.findScheduleByTimeInterval(startOfNextDay, endOfNextDay);
        return scheduleAfterTomorrow;
    }

    @Override
    public List<Schedule> findOverdueSchedule() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Schedule> overdueSchedule = scheduleRepository.findOverdueSchedule(currentDateTime);
        return overdueSchedule;
    }

    @Override
    public List<Schedule> findAllByStopsAndTimeForSearchToday(String stopName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime endOfDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);

        List<Schedule> scheduleToday = scheduleRepository.findAllByStopsAndTimeForSearch(stopName, currentDateTime, endOfDay);
        return scheduleToday;
    }

    @Override
    public List<Schedule> findAllByStopsAndTimeForSearchTomorrow(String stopName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleTomorrow = scheduleRepository.findAllByStopsAndTimeForSearch(stopName, startOfNextDay, endOfNextDay);
        return scheduleTomorrow;
    }

    @Override
    public List<Schedule> findAllByStopsAndTimeForSearchAfterTomorrow(String stopName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(2).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(2).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleAfterTomorrow = scheduleRepository.findAllByStopsAndTimeForSearch(stopName, startOfNextDay, endOfNextDay);
        return scheduleAfterTomorrow;
    }
}
