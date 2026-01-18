package com.ga.airticketmanagement.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingCreateDTO {

    private String passengerName;

    private String phoneNumber;

    private String fromCity;

    private String toCity;

    private LocalDate travelDate;

    private Integer numberOfSeats;

    private Double pricePerSeat;

    private String seatNo;

    private String flightNo;
}