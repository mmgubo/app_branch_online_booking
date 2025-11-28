package com.mfuras.booking.bookingsline;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsLineRepository extends JpaRepository<BookingsLine, Integer> {
    List<BookingsLine> findAllByBookingsId(Integer bookingsId);
}