package com.ga.airticketmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreateDTO {
    private Long id;  
    private String passengerName;

    private String phoneNumber;

    private String fromCity;

    private String toCity;

    private LocalDate travelDate;

    private Integer numberOfSeats;

    private Double pricePerSeat;

    private String seatNo;

    private String flightNo;

    @Override
    public String toString() {
        return "BookingCreateDTO{" +
                "passengerName='" + passengerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", travelDate=" + travelDate +
                ", numberOfSeats=" + numberOfSeats +
                ", pricePerSeat=" + pricePerSeat +
                ", seatNo='" + seatNo + '\'' +
                ", flightNo='" + flightNo + '\'' +
                '}';
    }
}