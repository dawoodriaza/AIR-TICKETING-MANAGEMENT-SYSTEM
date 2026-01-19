package com.ga.airticketmanagement.service;

import com.ga.airticketmanagement.model.Booking;
import com.ga.airticketmanagement.model.Payment;
import com.ga.airticketmanagement.model.SmsLog;
import com.ga.airticketmanagement.repository.SmsRepo;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    @Value("${twilio.from}")
    private String from;

    private final SmsRepo smsRepo;

    public SmsLog send(
            String phone,
            String message,
            String type,
            Booking booking,
            Payment payment
    ) {
        SmsLog log = new SmsLog();
        log.setPhoneNumber(phone);
        log.setMessage(message);
        log.setType(type);
        log.setBooking(booking);
        log.setPayment(payment);

        try {
            Message.creator(
                    new PhoneNumber(phone),
                    new PhoneNumber(from),
                    message
            ).create();

            log.setStatus("SENT");
        } catch (Exception e) {
            log.setStatus("FAILED");
            log.setMessage(message + " | ERROR: " + e.getMessage());
        }

        return smsRepo.save(log);
    }
}