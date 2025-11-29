package com.mfuras.booking.branch;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor

public class BranchController {

    private final BranchService service;

    @PostMapping
    public ResponseEntity<Integer> createBranch(
            @RequestBody @Valid BranchRequest request
    ){
        return ResponseEntity.ok(service.createBranch(request));
    }
}
