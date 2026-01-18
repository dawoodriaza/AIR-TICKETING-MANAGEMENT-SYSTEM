package com.ga.airticketmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "whatsapp_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WhatsAppLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String phoneNumber;

    @Column(length = 1000)
    private String message;

    @Column
    private String type;

    @Column
    private String status;

    @Column
    private String otpCode;

    @Column
    private LocalDateTime sentAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;
}