package com.mfuras.booking.bookingsline;


import com.mfuras.booking.bookings.Bookings;
import org.springframework.stereotype.Service;

@Service
public class BookingsLineMapper {
    public BookingsLine toBookingsLine(BookingLineRequest bookingLineRequest) {
        return BookingsLine.builder()
                .id(bookingLineRequest.id())
                .product(
                        Bookings.builder()
                                .id(bookingLineRequest.id())
                                .build()
                )
                .productId(bookingLineRequest.productId())
                .build();
    }

    public BookingsLineResponse toBookingsLineresponse(BookingsLine bookingsLine) {
        return new BookingsLineResponse(bookingsLine.getId());
    }
}
