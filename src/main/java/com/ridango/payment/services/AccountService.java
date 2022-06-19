package com.ridango.payment.services;

import com.ridango.payment.models.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account getAccountById(Long accountId) throws IllegalArgumentException;

    void transferMoney(Account sender, Account receiver, BigDecimal amount) throws IllegalArgumentException;
}
