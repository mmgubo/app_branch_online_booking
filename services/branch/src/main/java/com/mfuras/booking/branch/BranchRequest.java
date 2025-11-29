package com.mfuras.booking.branch;

public record BranchRequest(
        Integer id,
        Integer bookingsId,
        String bookingReference,
        Customer customer
) {
}
