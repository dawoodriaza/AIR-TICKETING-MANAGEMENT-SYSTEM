package com.ga.airticketmanagement.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponseDTO {

    private Long id;

    private String passengerName;

    private String phoneNumber;

    private String fromCity;

    private String toCity;

    private Integer numberOfSeats;

    private Double pricePerSeat;

    private Double totalPrice;

    private String status;

    private Boolean otpVerified;

    private String flightNo;
}