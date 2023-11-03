package com.busschedule.web.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "routes_stops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoutesStops {
    @EmbeddedId
    private RoutesStopsId id = new RoutesStopsId();
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId("routeId")
    private Route route;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId("stopId")
    private Stop stop;
    private Integer stopNumber;
    private Double price;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
}
