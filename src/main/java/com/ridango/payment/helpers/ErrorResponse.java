package com.ridango.payment.helpers;

import java.time.OffsetDateTime;
import java.util.List;

public class ErrorResponse {
    public final OffsetDateTime timestamp;
    public final List<String> errors;

    public ErrorResponse(List<String> errors) {
        this.timestamp = OffsetDateTime.now();
        this.errors = errors;
    }
}
