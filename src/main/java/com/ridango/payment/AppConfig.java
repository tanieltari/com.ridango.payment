package com.ridango.payment;

import com.ridango.payment.repositories.AccountRepository;
import com.ridango.payment.repositories.PaymentRepository;
import com.ridango.payment.services.AccountService;
import com.ridango.payment.services.AccountServiceImpl;
import com.ridango.payment.services.PaymentService;
import com.ridango.payment.services.PaymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    public AppConfig(AccountRepository accountRepository, PaymentRepository paymentRepository) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
    }

    @Bean
    public AccountService accountService() {
        return new AccountServiceImpl(accountRepository);
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentServiceImpl(paymentRepository, accountService());
    }
}
