package com.busschedule.web.dto;

import com.busschedule.web.models.RoutesStops;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopDto {
    private Long id;
    private String name;
    private List<RoutesStops> routesStops;
}
