package com.ridango.payment.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ridango.payment.models.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
