package com.ga.airticketmanagement.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentRequest {

    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

}