package com.ga.airticketmanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("passenger_name")
    private String passengerName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("from_city")
    private String fromCity;

    @JsonProperty("to_city")
    private String toCity;

    @JsonProperty("number_of_seats")
    private Integer numberOfSeats;

    @JsonProperty("price_per_seat")
    private Double pricePerSeat;

    @JsonProperty("total_price")
    private Double totalPrice;

    @JsonProperty("status")
    private String status;

    @JsonProperty("otp_verified")
    private Boolean otpVerified;

    @JsonProperty("flight_no")
    private String flightNo;
}
