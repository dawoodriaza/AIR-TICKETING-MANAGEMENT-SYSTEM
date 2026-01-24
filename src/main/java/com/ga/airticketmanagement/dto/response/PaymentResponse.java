package com.ga.airticketmanagement.dto.response;

import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long bookingId,
        Long userId,
        String userEmail,
        Double amount,
        String status,
        String transactionRef,
        LocalDateTime paidAt
) {}