package com.mfuras.booking.notification;

public record BranchNotificationRequest(
        String bookingsReference,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
