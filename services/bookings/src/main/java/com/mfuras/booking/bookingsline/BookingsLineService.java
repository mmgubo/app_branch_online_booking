package com.mfuras.booking.bookingsline;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingsLineService {
    private final BookingsLineRepository repository;
    private final BookingsLineMapper mapper;

    public Integer saveBookingLine(BookingLineRequest bookingLineRequest) {
        var bookings = mapper.toBookingsLine(bookingLineRequest);
        return repository.save(bookings).getId();
    }

    public List<BookingsLineResponse> findAllByBookingsId(Integer bookingsId) {
        return repository.findAllByBookingsId(bookingsId)
                .stream()
                .map(mapper::toBookingsLineresponse)
                .collect(Collectors.toList());

    }
}
