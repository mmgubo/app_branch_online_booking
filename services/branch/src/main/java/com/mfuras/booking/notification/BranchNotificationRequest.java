package com.mfuras.booking.notification;

public record BranchNotificationRequest(
        String bookingsReference,
        String customerName,
        String customerEmail
) {
}
