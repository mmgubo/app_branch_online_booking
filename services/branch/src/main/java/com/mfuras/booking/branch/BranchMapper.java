package com.mfuras.booking.branch;

import org.springframework.stereotype.Service;

@Service
public class BranchMapper {
    public Branch toBranch(BranchRequest request) {
        return Branch.builder()
                .id(request.id())
                .bookingsId(request.bookingsId())
                .build();
    }
}
