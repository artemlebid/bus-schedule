package com.busschedule.web.dto;

import com.busschedule.web.models.Route;
import com.busschedule.web.models.RoutesStopsId;
import com.busschedule.web.models.Stop;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class RoutesStopsDto {
    private RoutesStopsId id = new RoutesStopsId();
    private Route route;
    private Stop stop;
    private Integer stopNumber;
    private Double price;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
}
