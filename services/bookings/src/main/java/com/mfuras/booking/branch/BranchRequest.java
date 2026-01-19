package com.mfuras.booking.branch;

import com.mfuras.booking.customer.CustomerResponse;

public record BranchRequest(
        Integer bookingsId,
        String bookingReference
) {
}
