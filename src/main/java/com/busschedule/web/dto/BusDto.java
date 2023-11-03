package com.busschedule.web.dto;

import com.busschedule.web.models.Company;
import com.busschedule.web.models.Schedule;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusDto {
    private Long id;
    private String name;
    private String licensePlate;
    private List<Schedule> schedule;
    private Company company;
}
