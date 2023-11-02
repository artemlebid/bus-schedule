package com.busschedule.web.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
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
    @Column(unique = true)
    private String licensePlate;
    @OneToMany(mappedBy = "bus")
    private List<Schedule> schedule;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
