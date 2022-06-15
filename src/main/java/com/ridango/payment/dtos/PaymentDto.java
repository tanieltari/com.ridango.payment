package com.ridango.payment.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    @NotNull
    @Min(1)
    private Long senderAccountId;
    @NotNull
    @Min(1)
    private Long receiverAccountId;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(fraction = 2, integer = 10)
    private BigDecimal amount;
}
