package com.ridango.payment.services;

import com.ridango.payment.models.Account;
import com.ridango.payment.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long accountId) throws IllegalArgumentException {
        return accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException(String.format("Account id %s is invalid", accountId)));
    }

    public void transferMoney(Account sender, Account receiver, BigDecimal amount) throws IllegalArgumentException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount value must be greater than 0");
        }

        if (!hasAccountEnoughBalance(sender, amount)) {
            throw new IllegalArgumentException("Sender has not enough balance for payment");
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));
        accountRepository.saveAll(Arrays.asList(sender, receiver));
    }

    private boolean hasAccountEnoughBalance(Account account, BigDecimal amount) {
        return account.getBalance().compareTo(amount) >= 0;
    }
}
