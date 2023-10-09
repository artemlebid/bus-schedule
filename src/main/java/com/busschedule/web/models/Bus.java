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
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String licensePlate;
    @OneToMany(mappedBy = "bus")
    private List<Schedule> schedule;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
