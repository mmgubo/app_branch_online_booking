package com.mfuras.booking.notification;

public record BranchNotificationRequest(
        String orderReference,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
