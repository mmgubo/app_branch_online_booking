package com.mfuras.booking.bookings;

import com.mfuras.booking.branch.BranchClient;
import com.mfuras.booking.branch.BranchRequest;
import com.mfuras.booking.customer.CustomerClient;
import com.mfuras.booking.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingsService {

    private final BookingsRepository repository;
    private final CustomerClient customerClient;
    private final BookingsMapper mapper;
    private final BranchClient branchClient;

    public Integer createBooking(@Valid BookingsRequest request) {
        //check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId());

        var bookings = this.repository.save(mapper.toBookings(request));

        // branch process
        var branchRequest = new BranchRequest(
                bookings.getId(),
                bookings.getReference(),
                customer.getBody()
        );
        branchClient.requestBookingBranch(branchRequest);

        return bookings.getId();
    }

    public List<BookingsResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromBooking)
                .collect(Collectors.toList());

    }

    public BookingsResponse findById(Integer bookingsId) {
        return repository.findById(bookingsId)
                .map(mapper::fromBooking)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No booking found with the booking ID:: " + bookingsId)));
    }
}
