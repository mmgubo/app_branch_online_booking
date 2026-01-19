package com.mfuras.booking.branch;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name= "branch-service",
        url = "${application.config.branch-url}"
)
public interface BranchClient {

    @PostMapping
    Integer requestBookingBranch(@RequestBody BranchRequest request);
}
