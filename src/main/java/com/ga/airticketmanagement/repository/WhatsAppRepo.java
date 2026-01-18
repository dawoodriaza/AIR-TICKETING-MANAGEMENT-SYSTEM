package com.ga.airticketmanagement.repository;

import com.ga.airticketmanagement.model.WhatsAppLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WhatsAppRepo
        extends JpaRepository<WhatsAppLog, Long> {

    Optional<WhatsAppLog>
    findByBookingIdAndOtpCode(Long bookingId, String otp);
}
