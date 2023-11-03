package com.busschedule.web.dto;

import com.busschedule.web.models.RoutesStops;
import com.busschedule.web.models.Schedule;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDto {
    private Long id;
    private String name;
    private List<Schedule> schedule;
    private List<RoutesStops> routesStops;
}
