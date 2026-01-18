package com.ga.airticketmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    private String passengerName;

    @Column
    private String phoneNumber;

    @Column
    private String fromCity;

    @Column
    private String toCity;

    @Column
    private LocalDate travelDate;

    @Column
    private Integer numberOfSeats;

    @Column
    private Double pricePerSeat;

    @Column
    private Double totalPrice;

    @Column
    private String seatNo;

    @Column
    private String flightNo;

    @Column
    private String otp;

    @Column
    private Boolean otpVerified = false;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @PrePersist
    public void calculateTotalPrice() {
        if (pricePerSeat != null && numberOfSeats != null) {
            this.totalPrice = pricePerSeat * numberOfSeats;
        }
    }
}