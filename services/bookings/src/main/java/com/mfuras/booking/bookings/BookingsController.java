package com.mfuras.booking.bookings;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor

public class BookingsController {

    private final BookingsService service;

    @PostMapping("/createBooking")
    public ResponseEntity<Integer>createBooking(
            @RequestBody @Valid BookingsRequest request
    ){
        return ResponseEntity.ok(service.createBooking(request));
    }

    @GetMapping("/findAllBookings")
    public ResponseEntity<List<BookingsResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{bookings-id}")
    public ResponseEntity<BookingsResponse> findById(
            @PathVariable ("bookings-id") Integer bookingsId
    ){
        return ResponseEntity.ok(service.findById(bookingsId));
    }
}
