package com.mfuras.booking.handler;

import java.util.Map;

public record ErrorResponse (

        Map<String, String> errors
){
}
