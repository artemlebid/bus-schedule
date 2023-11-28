package com.busschedule.web.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class OrderForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromStop;
    private String toStop;
    private int countSeats;
    private Double payment;
    private String name;
    private String surname;
    private String phone;

    private String email;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    @CreationTimestamp
    private LocalDateTime created;
}
