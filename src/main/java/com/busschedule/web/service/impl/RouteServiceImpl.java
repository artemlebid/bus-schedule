package com.busschedule.web.service.impl;

import com.busschedule.web.dto.RouteDto;
import com.busschedule.web.models.Route;
import com.busschedule.web.repository.RouteRepository;
import com.busschedule.web.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public void saveRoute(RouteDto route) {
        routeRepository.save(mapToRoute(route));
    }

    @Override
    public List<RouteDto> findAllRoutes() {
        List<Route> allRoutes = routeRepository.findAll();
        return allRoutes.stream().map(route -> mapToRouteDto(route)).collect(Collectors.toList());
    }

    @Override
    public RouteDto findRouteById(Long id) {
        Route route = new Route();
        Optional<Route> optionalRoute = routeRepository.findById(id);

        if(optionalRoute.isPresent()){
            route = optionalRoute.get();
        }

        return mapToRouteDto(route);
    }

    private RouteDto mapToRouteDto(Route route){
        RouteDto routeDto = RouteDto.builder()
                .id(route.getId())
                .name(route.getName())
                .build();
        return routeDto;
    }

    private Route mapToRoute(RouteDto routeDto){
        Route route = Route.builder()
                .id(routeDto.getId())
                .name(routeDto.getName())
                .build();
        return route;
    }
}
