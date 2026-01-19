package com.ga.airticketmanagement.dto.response;

import lombok.Data;

@Data
public class PaymentDTO {

    private Long bookingId;

    private Double amount;

    private String method;
}

