package com.busschedule.web.dto;

import com.busschedule.web.models.Schedule;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
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
    @NotEmpty(message = "Departure place should not be empty")
    private String fromStop;
    @NotEmpty(message = "Arrival place should not be empty")
    private String toStop;
    @Min(value = 1, message = "Min count of seats should be 1")
    private int countSeats;
    @NotNull(message = "Payment should not be empty")
    private Double payment;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be from 2 to 30 symbols")
    private String name;
    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Name should be from 2 to 30 symbols")
    private String surname;
    @NotEmpty(message = "Phone should not be empty")
    @Size(min = 13, max = 13, message = "Phone should have 10 symbols")
    private String phone;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Incorrect email")
    private String email;
    private Schedule schedule;
    private LocalDateTime created;
}
