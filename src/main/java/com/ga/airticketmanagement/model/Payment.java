package com.ga.airticketmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double amount;

    @Column
    private String status;

    @Column
    private String transactionRef;

    @Column
    private LocalDateTime paidAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private SmsLog smsLog;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private WhatsAppLog whatsAppLog;
}