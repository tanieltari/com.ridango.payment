package com.ridango.payment.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ridango.payment.dtos.PaymentDto;
import com.ridango.payment.services.PaymentService;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/payment", consumes = "application/json", produces = "application/json")
    public void addPayment(@Valid @RequestBody PaymentDto payment) {
        paymentService.doPayment(payment);
    }
}
