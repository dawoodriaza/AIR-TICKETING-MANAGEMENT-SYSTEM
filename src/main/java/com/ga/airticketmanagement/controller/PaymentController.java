package com.ga.airticketmanagement.controller;

import com.ga.airticketmanagement.dto.response.PaymentDTO;
import com.ga.airticketmanagement.model.Payment;
import com.ga.airticketmanagement.service.PaymentService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public Payment pay(@RequestBody PaymentDTO dto) {
        return service.pay(dto);
    }

}

