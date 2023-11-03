package com.busschedule.web.controller;

import com.busschedule.web.dto.*;
import com.busschedule.web.models.*;
import com.busschedule.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
        List<ScheduleDto> scheduleToday = scheduleService.findScheduleToday();
        List<ScheduleDto> scheduleTomorrow = scheduleService.findScheduleTomorrow();
        List<ScheduleDto> scheduleAfterTomorrow = scheduleService.findScheduleAfterTomorrow();
        List<BusDto> buses = busService.findAllBuses();
        List<CompanyDto> companies = companyService.findAllCompanies();
        List<RouteDto> routes =  routeService.findAllRoutes();
        List<StopDto> stops = stopService.findAllStops();
        List<RoutesStopsDto> routesStops = routesStopsService.findAllSortedRoutesStops();
        List<OrderFormDto> orders = orderService.findAllSortedOrders();

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
        CompanyDto company = new CompanyDto();
        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("company", company);

        return "edit-company-form";
    }

    @PostMapping("/admin-panel/new/company")
    public String saveCompany(@ModelAttribute("company") CompanyDto company){
        companyService.saveCompany(company);
        return "redirect:/admin-panel";
    }

    @GetMapping("/admin-panel/edit/company/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        CompanyDto company = companyService.findCompanyById(id);

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("company", company);

        return "edit-company-form";
    }

    @GetMapping("/admin-panel/new/bus")
    public String addBus(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        List<CompanyDto> companies = companyService.findAllCompanies();
        BusDto bus = new BusDto();

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("companies", companies);
        model.addAttribute("bus", bus);

        return "edit-bus-form";
    }
    @GetMapping("/admin-panel/edit/bus/{id}")
    public String editBus(@PathVariable("id") Long id, Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        List<CompanyDto> companies = companyService.findAllCompanies();
        BusDto bus = busService.findBusById(id);

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("companies", companies);
        model.addAttribute("bus", bus);

        return "edit-bus-form";
    }
    @PostMapping("/admin-panel/new/bus")
    public String saveBus(@ModelAttribute("bus") BusDto bus){
        busService.saveBus(bus);
        return "redirect:/admin-panel";
    }

    @GetMapping("/admin-panel/new/stop")
    public String addStop(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        StopDto stop = new StopDto();

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("stop", stop);

        return "edit-stop-form";
    }

    @GetMapping("/admin-panel/edit/stop/{id}")
    public String editStop(@PathVariable("id") Long id, Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        StopDto stop = stopService.findStopById(id);

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("stop", stop);

        return "edit-stop-form";
    }

    @PostMapping("/admin-panel/new/stop")
    public String saveStop(@ModelAttribute("stop") StopDto stop){
        stopService.saveStop(stop);
        return "redirect:/admin-panel";
    }

    @GetMapping("/admin-panel/new/route")
    public String addRoute(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        RouteDto route = new RouteDto();

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("route", route);

        return "edit-route-form";
    }
    @GetMapping("/admin-panel/edit/route/{id}")
    public String editRoute(@PathVariable("id") Long id, Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        RouteDto route = routeService.findRouteById(id);

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("route", route);

        return "edit-route-form";
    }
    @PostMapping("/admin-panel/new/route")
    public String saveRoute(@ModelAttribute("route") RouteDto route){
        routeService.saveRoute(route);
        return "redirect:/admin-panel";
    }

    @GetMapping("/admin-panel/edit/routestop/{routeId}/{stopId}")
    public String editRouteStop(@PathVariable("routeId") Long routeId,
                                @PathVariable("stopId") Long stopId,
                                Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        List<StopDto> stops = stopService.findAllStops();
        List<RouteDto> routes = routeService.findAllRoutes();
        RoutesStopsDto routesStops = routesStopsService.findByRouteIdAndStopId(routeId, stopId);

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("stops", stops);
        model.addAttribute("routes", routes);
        model.addAttribute("routesStops", routesStops);

        return "edit-routestop-form";
    }

    @PostMapping("/admin-panel/new/routestop")
    public String saveRouteStop(@ModelAttribute("routesStops") RoutesStopsDto routesStops){
        LocalTime arrivalTime = routesStops.getArrivalTime();
        LocalTime departureTime = routesStops.getDepartureTime();
        Double price = routesStops.getPrice();
        Integer stopNumber = routesStops.getStopNumber();
        Long stopId = routesStops.getStop().getId();
        Long routeId = routesStops.getRoute().getId();
        routesStopsService.customSave(arrivalTime, departureTime, price, stopNumber, stopId, routeId);
        return "redirect:/admin-panel";
    }
    @GetMapping("/admin-panel/new/routestop")
    public String addRouteStop(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        List<StopDto> stops = stopService.findAllStops();
        List<RouteDto> routes = routeService.findAllRoutes();
        RoutesStopsDto routesStops = new RoutesStopsDto();

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("stops", stops);
        model.addAttribute("routes", routes);
        model.addAttribute("routesStops", routesStops);

        return "edit-routestop-form";
    }

    @GetMapping("/admin-panel/new/schedule")
    public String addSchedule(Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        List<BusDto> buses = busService.findAllBuses();
        List<RouteDto> routes = routeService.findAllRoutes();
        ScheduleDto schedule = new ScheduleDto();

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("buses", buses);
        model.addAttribute("routes", routes);
        model.addAttribute("schedule", schedule);

        return "edit-schedule-form";
    }

    @GetMapping("/admin-panel/edit/schedule/{id}")
    public String editSchedule(@PathVariable("id") Long id, Model model){
        LocalDateTime currentTime = LocalDateTime.now();
        SearchDto search = new SearchDto();
        List<BusDto> buses = busService.findAllBuses();
        List<RouteDto> routes = routeService.findAllRoutes();
        ScheduleDto schedule = scheduleService.findScheduleById(id);

        model.addAttribute("time", currentTime);
        model.addAttribute("search", search);
        model.addAttribute("buses", buses);
        model.addAttribute("routes", routes);
        model.addAttribute("schedule", schedule);

        return "edit-schedule-form";
    }
    @PostMapping("/admin-panel/new/schedule")
    public String saveSchedule(@ModelAttribute("schedule") ScheduleDto schedule){
        scheduleService.saveSchedule(schedule);
        return "redirect:/admin-panel";
    }
}
