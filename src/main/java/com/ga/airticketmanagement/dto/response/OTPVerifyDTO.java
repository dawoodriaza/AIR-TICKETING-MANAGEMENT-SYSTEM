package com.ga.airticketmanagement.dto.response;

import lombok.Data;

@Data
public class OTPVerifyDTO {
    private Long bookingId;
    private String otp;
}
