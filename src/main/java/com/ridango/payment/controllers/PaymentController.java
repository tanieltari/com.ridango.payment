package com.ridango.payment.controllers;

import com.ridango.payment.dtos.PaymentDto;
import com.ridango.payment.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/payment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> addPayment(@Valid @RequestBody PaymentDto payment) {
        paymentService.doPayment(payment);
        return ResponseEntity.accepted().build();
    }
}
