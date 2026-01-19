package com.ga.airticketmanagement.service;

import com.ga.airticketmanagement.dto.mapper.BookingMapper;
import com.ga.airticketmanagement.dto.request.BookingCreateDTO;
import com.ga.airticketmanagement.dto.response.BookingResponseDTO;
import com.ga.airticketmanagement.dto.response.OTPVerifyDTO;
import com.ga.airticketmanagement.exception.InformationNotFoundException;
import com.ga.airticketmanagement.model.Booking;
import com.ga.airticketmanagement.model.Flight;
import com.ga.airticketmanagement.model.User;
import com.ga.airticketmanagement.repository.BookingRepository;
import com.ga.airticketmanagement.repository.FlightRepository;
import com.ga.airticketmanagement.security.AuthenticatedUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final WhatsAppService whatsAppService;
    private final BookingMapper mapper;
    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final FlightRepository flightRepository;

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new InformationNotFoundException("Booking with Id " + bookingId + " not found"));
    }

    @Transactional
    public Booking updateBookingById(Long id, Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Request body is missing");
        }

        return bookingRepository.findById(id)
                .map(existingBooking -> {
                    existingBooking.setTotalPrice(booking.getTotalPrice());
                    existingBooking.setStatus(booking.getStatus());
                    existingBooking.setNumberOfSeats(booking.getNumberOfSeats());
                    return bookingRepository.save(existingBooking);
                })
                .orElseThrow(() ->
                        new InformationNotFoundException("Booking with Id " + id + " not found"));
    }

    @Transactional
    public Booking createBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Request body is missing");
        }
        return bookingRepository.save(booking);
    }

    @Transactional
    public void deleteBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new InformationNotFoundException("Booking with Id " + id + " not found"));
        bookingRepository.delete(booking);
    }
    @Transactional
    public BookingResponseDTO create(BookingCreateDTO dto) {
        Booking booking = mapper.toEntity(dto);
        User currentUser = authenticatedUserProvider.getAuthenticatedUser();
        booking.setUser(currentUser);


        if (dto.getFlightNo() != null) {
            Flight flight = flightRepository.findByFlightNo(dto.getFlightNo())
                    .orElseThrow(() -> new InformationNotFoundException("Flight not found"));
            booking.setFlight(flight);
        }

        booking.setStatus("CREATED");
        String otp = String.valueOf(new Random().nextInt(9000) + 1000);
        booking.setOtp(otp);
        Booking saved = bookingRepository.save(booking);


        if (saved.getPhoneNumber() != null) {
            whatsAppService.send(
                    saved.getPhoneNumber(),
                    "Your OTP for booking verification: " + otp,
                    "OTP",
                    saved,
                    null,
                    otp
            );
        }

        return mapper.toDTO(saved);
    }


    @Transactional
    public String verifyOtp(OTPVerifyDTO dto) {
        if (dto == null || dto.getBookingId() == null) {
            throw new IllegalArgumentException("Booking ID must not be null");
        }
        Booking booking = bookingRepository.findById(dto.getBookingId())
                .orElseThrow(() -> new InformationNotFoundException("Booking not found"));
        if (!dto.getOtp().equals(booking.getOtp())) {
            throw new InformationNotFoundException("Invalid OTP");
        }

        booking.setOtpVerified(true);
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);

        whatsAppService.send(
                booking.getPhoneNumber(),
                "Booking Confirmed!\nFlight: " + booking.getFlightNo() +
                        "\nFrom: " + booking.getFromCity() + " To: " + booking.getToCity() +
                        "\nSeats: " + booking.getNumberOfSeats() +
                        "\nTotal: $" + booking.getTotalPrice(),
                "BOOKING",
                booking,
                null,
                null
        );

        return "VERIFIED";
    }

}