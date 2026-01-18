package com.ga.airticketmanagement.dto.mapper;

import com.ga.airticketmanagement.dto.request.BookingCreateDTO;
import com.ga.airticketmanagement.dto.response.BookingResponseDTO;
import com.ga.airticketmanagement.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking toEntity(BookingCreateDTO dto) {
        Booking b = new Booking();
        b.setPassengerName(dto.getPassengerName());
        b.setPhoneNumber(dto.getPhoneNumber());
        b.setFromCity(dto.getFromCity());
        b.setToCity(dto.getToCity());
        b.setTravelDate(dto.getTravelDate());
        b.setNumberOfSeats(dto.getNumberOfSeats());
        b.setPricePerSeat(dto.getPricePerSeat());
        b.setSeatNo(dto.getSeatNo());
        b.setFlightNo(dto.getFlightNo());
        return b;
    }

    public BookingResponseDTO toDTO(Booking b) {
        return BookingResponseDTO.builder()
                .id(b.getId())
                .passengerName(b.getPassengerName())
                .phoneNumber(b.getPhoneNumber())
                .fromCity(b.getFromCity())
                .toCity(b.getToCity())
                .numberOfSeats(b.getNumberOfSeats())
                .pricePerSeat(b.getPricePerSeat())
                .totalPrice(b.getTotalPrice())
                .status(b.getStatus())
                .otpVerified(b.getOtpVerified())
                .flightNo(b.getFlightNo())
                .build();
    }
}