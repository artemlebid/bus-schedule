package com.busschedule.web.service.impl;

import com.busschedule.web.dto.ScheduleDto;
import com.busschedule.web.models.Schedule;
import com.busschedule.web.repository.ScheduleRepository;
import com.busschedule.web.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDto findScheduleById(Long id) {
        Schedule schedule = new Schedule();
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        if(optionalSchedule.isPresent()){
            schedule = optionalSchedule.get();
        }
        return mapToScheduleDto(schedule);
    }

    @Override
    public void saveSchedule(ScheduleDto schedule) {
        scheduleRepository.save(mapToSchedule(schedule));
    }

    @Override
    public void saveScheduleList(List<ScheduleDto> schedules) {
        List<Schedule> scheduleList = schedules.stream().map(scheduleDto -> mapToSchedule(scheduleDto)).collect(Collectors.toList());
        scheduleRepository.saveAll(scheduleList);
    }

    @Override
    public List<ScheduleDto> findScheduleToday() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime endOfDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleToday = scheduleRepository.findScheduleByTimeInterval(currentDateTime, endOfDay);

        return scheduleToday.stream().map(schedule -> mapToScheduleDto(schedule)).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findScheduleTomorrow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleTomorrow = scheduleRepository.findScheduleByTimeInterval(startOfNextDay, endOfNextDay);

        return scheduleTomorrow.stream().map(schedule -> mapToScheduleDto(schedule)).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findScheduleAfterTomorrow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(2).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleAfterTomorrow = scheduleRepository.findScheduleByTimeInterval(startOfNextDay, endOfNextDay);

        return scheduleAfterTomorrow.stream().map(schedule -> mapToScheduleDto(schedule)).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findOverdueSchedule() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Schedule> overdueSchedule = scheduleRepository.findOverdueSchedule(currentDateTime);

        return overdueSchedule.stream().map(schedule -> mapToScheduleDto(schedule)).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findAllByStopsAndTimeForSearchToday(String stopName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime endOfDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleToday = scheduleRepository.findAllByStopsAndTimeForSearch(stopName, currentDateTime, endOfDay);

        return scheduleToday.stream().map(schedule -> mapToScheduleDto(schedule)).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findAllByStopsAndTimeForSearchTomorrow(String stopName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(1).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleTomorrow = scheduleRepository.findAllByStopsAndTimeForSearch(stopName, startOfNextDay, endOfNextDay);

        return scheduleTomorrow.stream().map(schedule -> mapToScheduleDto(schedule)).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> findAllByStopsAndTimeForSearchAfterTomorrow(String stopName) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfNextDay = currentDateTime.toLocalDate().plusDays(2).atTime(LocalTime.MIDNIGHT);
        LocalDateTime endOfNextDay = startOfNextDay.toLocalDate().plusDays(2).atTime(LocalTime.MIDNIGHT);
        List<Schedule> scheduleAfterTomorrow = scheduleRepository.findAllByStopsAndTimeForSearch(stopName, startOfNextDay, endOfNextDay);

        return scheduleAfterTomorrow.stream().map(schedule -> mapToScheduleDto(schedule)).collect(Collectors.toList());
    }

    private ScheduleDto mapToScheduleDto(Schedule schedule){
        ScheduleDto scheduleDto = ScheduleDto.builder()
                .id(schedule.getId())
                .accessibility(schedule.getAccessibility())
                .arrivalTime(schedule.getArrivalTime())
                .departureTime(schedule.getDepartureTime())
                .price(schedule.getPrice())
                .seats(schedule.getSeats())
                .bus(schedule.getBus())
                .route(schedule.getRoute())
                .build();
        return scheduleDto;
    }

    private Schedule mapToSchedule(ScheduleDto scheduleDto){
        Schedule schedule = Schedule.builder()
                .id(scheduleDto.getId())
                .accessibility(scheduleDto.getAccessibility())
                .arrivalTime(scheduleDto.getArrivalTime())
                .departureTime(scheduleDto.getDepartureTime())
                .price(scheduleDto.getPrice())
                .seats(scheduleDto.getSeats())
                .bus(scheduleDto.getBus())
                .route(scheduleDto.getRoute())
                .build();
        return schedule;
    }
}
