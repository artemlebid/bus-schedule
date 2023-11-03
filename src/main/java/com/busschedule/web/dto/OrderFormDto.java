package com.busschedule.web.dto;

import com.busschedule.web.models.Schedule;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFormDto {
    private Long id;
    private String fromStop;
    private String toStop;
    private int countSeats;
    private Double payment;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Schedule schedule;
    private LocalDateTime created;
}
