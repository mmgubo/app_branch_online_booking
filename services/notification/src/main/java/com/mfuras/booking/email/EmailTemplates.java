package com.mfuras.booking.email;

import lombok.Getter;

public enum EmailTemplates {

    BRANCH_CONFIRMATION("branch-confirmation.html", "Branch confirmation"),

    BOOKINGS_CONFIRMATION("booking-confirmation.html", "Booking confirmation")
    ;

    @Getter
    private final String template;
    @Getter
    private final String subject;
    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
