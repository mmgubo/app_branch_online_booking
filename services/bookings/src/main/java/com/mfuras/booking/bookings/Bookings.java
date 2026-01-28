package com.mfuras.booking.bookings;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "customer_bookings")
public class Bookings {
    @Id
    @GeneratedValue
    private Integer id;

    private String service;

    private String customerId;

    private String date;

    private String time;

    private String status;

    private String branch;

    private String notes;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

}
