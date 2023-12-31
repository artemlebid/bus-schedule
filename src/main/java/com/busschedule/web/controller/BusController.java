package com.busschedule.web.controller;

import com.busschedule.web.dto.OrderFormDto;
import com.busschedule.web.dto.ScheduleDto;
import com.busschedule.web.dto.SearchDto;
import com.busschedule.web.models.RoutesStops;
import com.busschedule.web.models.Schedule;
import com.busschedule.web.service.OrderService;
import com.busschedule.web.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BusController {
    private ScheduleService scheduleService;
    private OrderService orderService;

    @Autowired
    public BusController(ScheduleService scheduleService, OrderService orderService) {
        this.scheduleService = scheduleService;
        this.orderService = orderService;
    }

    @GetMapping("/schedule")
    public String scheduleList(Model model){
        LocalDateTime currentTime = LocalDateTime.now();

        List<ScheduleDto> overdueSchedule = scheduleService.findOverdueSchedule();
        for(ScheduleDto schedule : overdueSchedule){
            LocalDateTime departureTime = schedule.getDepartureTime();
            LocalDateTime arrivalTime = schedule.getArrivalTime();
            schedule.setDepartureTime(departureTime.plusDays(3));
            schedule.setArrivalTime(arrivalTime.plusDays(3));
            schedule.setSeats(30);
            schedule.setAccessibility("У продажі");
        }
        scheduleService.saveScheduleList(overdueSchedule);

        List<ScheduleDto> scheduleToday = scheduleService.findScheduleToday();
        List<ScheduleDto> scheduleTomorrow = scheduleService.findScheduleTomorrow();
        List<ScheduleDto> scheduleAfterTomorrow = scheduleService.findScheduleAfterTomorrow();
        SearchDto search = new SearchDto();

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("scheduleToday", scheduleToday);
        model.addAttribute("scheduleTomorrow", scheduleTomorrow);
        model.addAttribute("scheduleAfterTomorrow", scheduleAfterTomorrow);

        return "buses-list";
    }

    @GetMapping("/schedule/{scheduleId}")
    public String scheduleDetail(@PathVariable("scheduleId") Long id, Model model){
        ScheduleDto schedule = scheduleService.findScheduleById(id);
        List<RoutesStops> routesStops = schedule.getRoute().getRoutesStops(); //????????????????????????????/
        routesStops.sort((o1,o2) -> o1.getStopNumber() - o2.getStopNumber());
        SearchDto searchDto = new SearchDto();
        model.addAttribute("search", searchDto);
        model.addAttribute("schedule", schedule);
        model.addAttribute("routesStops", routesStops);
        model.addAttribute("orderForm", new OrderFormDto());

        return "buses-detail";
    }
    @PostMapping("/schedule/order")
    public String scheduleOrder(@ModelAttribute("orderForm") OrderFormDto orderForm, BindingResult result, @ModelAttribute("schedule")Schedule schedule){
        if(result.hasErrors()){
            return "buses-error";
        }
        schedule.setSeats(schedule.getSeats() - orderForm.getCountSeats());
        if(schedule.getSeats() == 0){
            schedule.setAccessibility("Продано!");
        }
        orderService.saveOrder(orderForm);
        return "buses-order";
    }

    @GetMapping("/schedule/search")
    public String searchBuses(@Valid @ModelAttribute("search") SearchDto search,
                              BindingResult bindingResult,
                              Model model){
        if(bindingResult.hasErrors()){
            LocalDateTime currentTime = LocalDateTime.now();
            model.addAttribute("time", currentTime);
            model.addAttribute("search", search);
//            model.addAttribute("scheduleToday", oldScheduleToday);
//            model.addAttribute("scheduleTomorrow", oldScheduleTomorrow);
//            model.addAttribute("scheduleAfterTomorrow", oldScheduleAfterTomorrow);

            return "buses-list";
        }

        LocalDateTime currentTime = LocalDateTime.now();
        List<ScheduleDto> scheduleToday = scheduleService.findAllByStopsAndTimeForSearchToday(search.getSearchLine());
        List<ScheduleDto> scheduleTomorrow = scheduleService.findAllByStopsAndTimeForSearchTomorrow(search.getSearchLine());
        List<ScheduleDto> scheduleAfterTomorrow = scheduleService.findAllByStopsAndTimeForSearchAfterTomorrow(search.getSearchLine());

        model.addAttribute("time", currentTime);
        model.addAttribute("scheduleToday", scheduleToday);
        model.addAttribute("scheduleTomorrow", scheduleTomorrow);
        model.addAttribute("scheduleAfterTomorrow", scheduleAfterTomorrow);
        model.addAttribute("search", search);

        return "buses-list";
    }
}
