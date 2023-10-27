package com.busschedule.web.controller;

import com.busschedule.web.dto.SearchDto;
import com.busschedule.web.models.*;
import com.busschedule.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AdminController {
    private ScheduleService scheduleService;
    private BusService busService;
    private CompanyService companyService;
    private RouteService routeService;
    private StopService stopService;
    private OrderService orderService;
    private RoutesStopsService routesStopsService;


    @Autowired
    public AdminController(ScheduleService scheduleService, BusService busService, CompanyService companyService, RouteService routeService, StopService stopService, OrderService orderService, RoutesStopsService routesStopsService) {
        this.scheduleService = scheduleService;
        this.busService = busService;
        this.companyService = companyService;
        this.routeService = routeService;
        this.stopService = stopService;
        this.orderService = orderService;
        this.routesStopsService = routesStopsService;
    }

    @GetMapping("/admin-panel")
    public String adminPanel(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        List<Schedule> scheduleToday = scheduleService.findScheduleToday();
        List<Schedule> scheduleTomorrow = scheduleService.findScheduleTomorrow();
        List<Schedule> scheduleAfterTomorrow = scheduleService.findScheduleAfterTomorrow();
        List<Bus> buses = busService.findAllBuses();
        List<Company> companies = companyService.findAllCompanies();
        List<Route> routes =  routeService.findAllRoutes();
        List<Stop> stops = stopService.findAllStops();
        List<RoutesStops> routesStops = routesStopsService.findAllSortedRoutesStops();
        List<OrderForm> orders = orderService.findAllSortedOrders();

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("scheduleToday", scheduleToday);
        model.addAttribute("scheduleTomorrow", scheduleTomorrow);
        model.addAttribute("scheduleAfterTomorrow", scheduleAfterTomorrow);
        model.addAttribute("buses", buses);
        model.addAttribute("companies", companies);
        model.addAttribute("routes", routes);
        model.addAttribute("stops", stops);
        model.addAttribute("routesStops", routesStops);
        model.addAttribute("orders", orders);
        return "buses-adminpanel";
    }

    @GetMapping("/admin-panel/new/company")
    public String addCompany(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);

        return "edit-company-form";
    }

    @GetMapping("/admin-panel/new/bus")
    public String addBus(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        List<Company> companies = companyService.findAllCompanies();

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("companies", companies);

        return "edit-bus-form";
    }

    @GetMapping("/admin-panel/new/stop")
    public String addStop(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);

        return "edit-stop-form";
    }

    @GetMapping("/admin-panel/new/route")
    public String addRoute(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);

        return "edit-route-form";
    }

    @GetMapping("/admin-panel/new/routestop")
    public String addRouteStop(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);

        return "edit-routestop-form";
    }

    @GetMapping("/admin-panel/new/schedule")
    public String addSchedule(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);

        return "edit-schedule-form";
    }
}
