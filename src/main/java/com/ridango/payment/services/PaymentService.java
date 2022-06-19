package com.ridango.payment.services;

import com.ridango.payment.dtos.PaymentDto;

public interface PaymentService {
    void doPayment(PaymentDto payment) throws IllegalArgumentException;
}
