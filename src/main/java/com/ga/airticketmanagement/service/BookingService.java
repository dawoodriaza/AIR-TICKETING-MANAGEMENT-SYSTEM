package com.ga.airticketmanagement.service;

import com.ga.airticketmanagement.dto.mapper.BookingMapper;
import com.ga.airticketmanagement.dto.request.BookingCreateDTO;
import com.ga.airticketmanagement.dto.response.BookingResponseDTO;
import com.ga.airticketmanagement.dto.response.OTPVerifyDTO;
import com.ga.airticketmanagement.exception.InformationNotFoundException;
import com.ga.airticketmanagement.model.Booking;
import com.ga.airticketmanagement.repository.BookingRepository;
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
        Booking b = mapper.toEntity(dto);
        b.setStatus("CREATED");

        String otp = String.valueOf(new Random().nextInt(9000) + 1000);
        b.setOtp(otp);

        bookingRepository.save(b);

        whatsAppService.send(
                b.getPhoneNumber(),
                "🔐 Your OTP for booking verification: " + otp,
                "OTP",
                b,
                null,
                otp
        );

        return mapper.toDTO(b);
    }

    @Transactional
    public String verifyOtp(OTPVerifyDTO dto) {
        Booking b = bookingRepository.findById(dto.getBookingId())
                .orElseThrow(() -> new InformationNotFoundException("Booking not found"));

        if (!b.getOtp().equals(dto.getOtp())) {
            throw new InformationNotFoundException("Invalid OTP");
        }

        b.setOtpVerified(true);
        b.setStatus("CONFIRMED");

        bookingRepository.save(b);

        whatsAppService.send(
                b.getPhoneNumber(),
                "✅ Booking Confirmed!\nFlight: " + b.getFlightNo() +
                        "\nFrom: " + b.getFromCity() + " To: " + b.getToCity() +
                        "\nSeats: " + b.getNumberOfSeats() +
                        "\nTotal: $" + b.getTotalPrice(),
                "BOOKING",
                b,
                null,
                null
        );

        return "VERIFIED";
    }
}