package com.mfuras.booking.bookingsline;

import com.mfuras.booking.bookings.BookingsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings-line")

public class BookingsLineController {
    private final BookingsLineService service;

    @GetMapping("/bookings/{bookings-id}")
    public ResponseEntity<List<BookingsLineResponse>> findByBookingsId(
            @PathVariable("bookings-id") Integer bookingsId
    ){
        return ResponseEntity.ok(service.findAllByBookingsId(bookingsId));
    }

}
