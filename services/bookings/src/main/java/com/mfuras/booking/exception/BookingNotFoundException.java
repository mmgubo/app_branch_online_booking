package com.mfuras.booking.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data

public class BookingNotFoundException extends  RuntimeException {
    private String message;
}
