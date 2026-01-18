package com.ga.airticketmanagement.controller;

import com.ga.airticketmanagement.dto.request.BookingCreateDTO;
import com.ga.airticketmanagement.dto.response.BookingResponseDTO;
import com.ga.airticketmanagement.dto.response.OTPVerifyDTO;
import com.ga.airticketmanagement.model.Booking;
import com.ga.airticketmanagement.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getBookings());
    }

    @PostMapping
    public BookingResponseDTO create(@RequestBody BookingCreateDTO dto) {
        return bookingService.create(dto);
    }

    @PostMapping("/verify")
    public String verify(@RequestBody OTPVerifyDTO dto) {
        return bookingService.verifyOtp(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long id,
            @RequestBody Booking booking
    ) {
        return ResponseEntity.ok(bookingService.updateBookingById(id, booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.ok("Booking with ID " + id + " deleted successfully.");
    }
}