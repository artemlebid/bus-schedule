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
    //    @NotEmpty(message = "Departure place should not be empty")
    private String fromStop;
    //    @NotEmpty(message = "Arrival place should not be empty")
    private String toStop;
    //    @Min(value = 1, message = "Min count of seats should be 1")
    private int countSeats;
    //    @NotEmpty(message = "Payment should not be empty")
    private Double payment;
    //    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2, max = 30, message = "Name should be from 2 to 30 symbols")
    private String name;
    //    @NotEmpty(message = "Surname should not be empty")
//    @Size(min = 2, max = 30, message = "Name should be from 2 to 30 symbols")
    private String surname;
    //    @NotEmpty(message = "Phone should not be empty")
//    @Size(min = 13, max = 13, message = "Phone should have 10 symbols")
    private String phone;
    //    @NotEmpty(message = "Email should not be empty")
//    @Email(message = "Incorrect email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    @CreationTimestamp
    private LocalDateTime created;
}
