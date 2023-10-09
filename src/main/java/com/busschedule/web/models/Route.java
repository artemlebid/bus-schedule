package com.busschedule.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "route")
    private List<Schedule> schedule;
    @OneToMany(
            mappedBy = "route",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RoutesStops> routesStops;
}
