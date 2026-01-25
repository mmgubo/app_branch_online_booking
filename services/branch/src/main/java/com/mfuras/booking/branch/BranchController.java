package com.mfuras.booking.branch;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor

public class BranchController {

    private final BranchService service;

    @PostMapping("/createBranch")
    public ResponseEntity<Integer> createBranch(
            @RequestBody @Valid BranchRequest request
    ){
        return ResponseEntity.ok(service.createBranch(request));
    }

    @DeleteMapping("/deleteBooking/{bookings-id}")
    public ResponseEntity<Void> deleteBranch(
            @PathVariable ("bookings-id") Integer bookingsId
    ){
        service.deleteBranch(bookingsId);
        return ResponseEntity.accepted().build();
    }
}
