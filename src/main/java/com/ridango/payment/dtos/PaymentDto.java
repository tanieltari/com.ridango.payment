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
    @NotNull(message = "Sender account ID must be valid number")
    @Min(value = 1, message = "Sender account ID must be positive number")
    private Long senderAccountId;
    @NotNull(message = "Receiver account ID must be valid number")
    @Min(value = 1, message = "Receiver account ID must be positive number")
    private Long receiverAccountId;
    @NotNull(message = "Amount must be valid number")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive number")
    @Digits(fraction = 2, integer = 10, message = "Amount must have 2 fraction part digits, eg 0.00")
    private BigDecimal amount;
}
