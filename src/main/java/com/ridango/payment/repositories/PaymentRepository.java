package com.ridango.payment.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ridango.payment.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
