package com.busschedule.web.dto;

import com.busschedule.web.models.Bus;
import com.busschedule.web.models.Route;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDto {
    private Long id;
    private Bus bus;
    private Route route;
    private Double price;
    private String accessibility;
    private Integer seats;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
}
