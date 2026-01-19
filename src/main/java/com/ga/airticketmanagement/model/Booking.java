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

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "to_city")
    private String toCity;

    @Column(name = "travel_date")
    private LocalDate travelDate;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "price_per_seat")
    private Double pricePerSeat;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "seat_no")
    private String seatNo;

    @Column(name = "flight_no")
    private String flightNo;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_verified")
    private Boolean otpVerified = false;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @PrePersist
    public void prePersist() {
        if (pricePerSeat != null && numberOfSeats != null) {
            this.totalPrice = pricePerSeat * numberOfSeats;
        }
        if (otpVerified == null) {
            this.otpVerified = false;
        }
    }
}