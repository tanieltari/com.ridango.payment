package com.ridango.payment.services;

import com.ridango.payment.dtos.PaymentDto;
import com.ridango.payment.models.Account;
import com.ridango.payment.models.Payment;
import com.ridango.payment.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountService accountService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, AccountService accountService) {
        this.paymentRepository = paymentRepository;
        this.accountService = accountService;
    }

    public void doPayment(PaymentDto payment) throws IllegalArgumentException {
        // Check if sender and receiver account id-s are the same
        if (payment.getSenderAccountId().compareTo(payment.getReceiverAccountId()) == 0) {
            throw new IllegalArgumentException("Sender and receiver account id-s cannot be the same");
        }

        // Get accounts
        Account sender = accountService.getAccountById(payment.getSenderAccountId());
        Account receiver = accountService.getAccountById(payment.getReceiverAccountId());

        // All gucci so far, so transaction can happen 👌
        accountService.transferMoney(sender, receiver, payment.getAmount());
        paymentRepository.save(Payment
                .builder()
                .sender(sender)
                .receiver(receiver)
                .build()
        );
    }
}
