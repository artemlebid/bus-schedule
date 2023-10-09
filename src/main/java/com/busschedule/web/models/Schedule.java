package com.busschedule.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "buses_id")
    private Bus bus;
    @ManyToOne
    @JoinColumn(name = "routes_id")
    private Route route;
    private Double price;
    private String accessibility;
    private Integer seats;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
}
