package com.ga.airticketmanagement.service;

import com.ga.airticketmanagement.model.Booking;
import com.ga.airticketmanagement.model.Payment;
import com.ga.airticketmanagement.model.WhatsAppLog;
import com.ga.airticketmanagement.repository.WhatsAppRepo;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WhatsAppService {

    @Value("${twilio.whatsappFrom}")
    private String from;

    private final WhatsAppRepo repo;

    public WhatsAppLog send(
            String phone,
            String message,
            String type,
            Booking booking,
            Payment payment,
            String otp
    ) {
        WhatsAppLog log = new WhatsAppLog();
        log.setPhoneNumber(phone);
        log.setMessage(message);
        log.setType(type);
        log.setBooking(booking);
        log.setPayment(payment);
        log.setOtpCode(otp);

        try {
            Message.creator(
                    new PhoneNumber("whatsapp:" + phone),
                    new PhoneNumber(from),
                    message
            ).create();

            log.setStatus("SENT");
        } catch (Exception e) {
            log.setStatus("FAILED");
            log.setMessage(message + " | ERROR: " + e.getMessage());
        }

        return repo.save(log);
    }
}