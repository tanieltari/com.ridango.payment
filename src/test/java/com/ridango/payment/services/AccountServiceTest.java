package com.ridango.payment.services;

import com.ridango.payment.PaymentApplication;
import com.ridango.payment.models.Account;
import com.ridango.payment.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = PaymentApplication.class)
public class AccountServiceTest {
    private static final Long SENDER_ACCOUNT_ID = 1L;
    private static final Long RECEIVER_ACCOUNT_ID = 2L;

    @InjectMocks
    private AccountServiceImpl service;
    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transferMoney_ShouldThrowException_WhenAmountIsNotGreaterThanZero() {
        Account sender = Account
                .builder()
                .id(SENDER_ACCOUNT_ID)
                .balance(BigDecimal.ONE)
                .build();
        Account receiver = Account
                .builder()
                .id(RECEIVER_ACCOUNT_ID)
                .balance(BigDecimal.ZERO)
                .build();
        // Zero value
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.transferMoney(sender, receiver, BigDecimal.ZERO));
        assertEquals("Amount value must be greater than 0", exception.getMessage());

        // Negative value
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> service.transferMoney(sender, receiver, BigDecimal.valueOf(-15.00)));
        assertEquals("Amount value must be greater than 0", exception2.getMessage());
    }

    @Test
    void transferMoney_ShouldThrowException_WhenBalanceIsNotEnoughForPayment() {
        Account sender = Account
                .builder()
                .id(SENDER_ACCOUNT_ID)
                .balance(BigDecimal.ONE)
                .build();
        Account receiver = Account
                .builder()
                .id(RECEIVER_ACCOUNT_ID)
                .balance(BigDecimal.ZERO)
                .build();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.transferMoney(sender, receiver, BigDecimal.TEN));
        assertEquals("Sender has not enough balance for payment", exception.getMessage());
    }

    @Test
    void transferMoney_ShouldPass_WhenAccountHasExactBalanceForPayment() {
        Account sender = Account
                .builder()
                .id(SENDER_ACCOUNT_ID)
                .balance(BigDecimal.ONE)
                .build();
        Account receiver = Account
                .builder()
                .id(RECEIVER_ACCOUNT_ID)
                .balance(BigDecimal.ZERO)
                .build();
        service.transferMoney(sender, receiver, BigDecimal.ONE);
        assertEquals(0, sender.getBalance().compareTo(BigDecimal.ZERO));
        assertEquals(0, receiver.getBalance().compareTo(BigDecimal.ONE));
    }
}
