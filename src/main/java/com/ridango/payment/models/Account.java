package com.ridango.payment.models;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal balance;

    @OneToMany(mappedBy = "sender")
    private Set<Payment> withdrawals;
    @OneToMany(mappedBy = "receiver")
    private Set<Payment> deposits;
}
