package com.ridango.payment.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridango.payment.dtos.PaymentDto;
import com.ridango.payment.models.Account;
import com.ridango.payment.models.Payment;
import com.ridango.payment.repositories.AccountRepository;
import com.ridango.payment.repositories.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, AccountRepository accountRepository) {
        this.paymentRepository = paymentRepository;
        this.accountRepository = accountRepository;
    }

    public void doPayment(PaymentDto payment) throws IllegalArgumentException {
        // Check if sender and receiver account id-s are the same
        if (payment.getSenderAccountId().compareTo(payment.getReceiverAccountId()) == 0) {
            throw new IllegalArgumentException("Sender and receiver account id-s cannot be the same");
        }

        // Check if sender account exists
        Optional<Account> senderAccount = accountRepository.findById(payment.getSenderAccountId());
        if (!senderAccount.isPresent()) {
            throw new IllegalArgumentException("Sender account id is invalid");
        }

        // Check if receiver account exists
        Optional<Account> receiverAccount = accountRepository.findById(payment.getReceiverAccountId());
        if (!receiverAccount.isPresent()) {
            throw new IllegalArgumentException("Receiver account id is invalid");
        }

        // Accounts
        Account sender = senderAccount.get();
        Account receiver = receiverAccount.get();

        // Check if sender has enough balance for payment
        if (sender.getBalance().compareTo(payment.getAmount()) < 0) {
            throw new IllegalArgumentException("Sender has not enough balance for payment");
        }

        // All gucci so far, so transaction can happen 👌
        sender.changeBalance(payment.getAmount().negate());
        receiver.changeBalance(payment.getAmount());
        accountRepository.saveAll(Arrays.asList(sender, receiver));
        Payment newPayment = new Payment();
        newPayment.setReceiver(receiver);
        newPayment.setSender(sender);
        paymentRepository.save(newPayment);
        return;
    }
}
