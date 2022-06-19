package com.ridango.payment.services;

import com.ridango.payment.dtos.PaymentDto;
import com.ridango.payment.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentServiceTest {
    private static final Long ACCOUNT_ID = 1L;

    @InjectMocks
    private PaymentServiceImpl service;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private AccountService accountService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doPayment_ShouldThrowException_WhenAccountIdsAreTheSame() {
        PaymentDto payment = new PaymentDto(ACCOUNT_ID, ACCOUNT_ID, BigDecimal.TEN);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.doPayment(payment));
        assertEquals("Sender and receiver account id-s cannot be the same", exception.getMessage());
    }
}