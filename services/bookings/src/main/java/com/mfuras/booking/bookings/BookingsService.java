package com.mfuras.booking.bookings;

import com.mfuras.booking.branch.BranchClient;
import com.mfuras.booking.branch.BranchRequest;
import com.mfuras.booking.customer.CustomerClient;
import com.mfuras.booking.exception.BookingNotFoundException;
import com.mfuras.booking.exception.BusinessException;
import com.mfuras.booking.kafka.BookConfirmation;
import com.mfuras.booking.kafka.BookProducer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class BookingsService {

    private final BookingsRepository repository;
    private final CustomerClient customerClient;
    private final BookingsMapper mapper;
    private final BranchClient branchClient;
    private final BookProducer bookProducer;

    public Integer createBooking(@Valid BookingsRequest request) {
        //check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId());

        var bookings = this.repository.save(mapper.toBookings(request));

        // branch process
        var branchRequest = new BranchRequest(
                bookings.getId(),
                bookings.getService(),
                customer.getBody()
        );
        branchClient.requestBookingBranch(branchRequest);

        bookProducer.sendBookConfirmation(
                new BookConfirmation(
                        request.service(),
                        customer.getBody()
                )
        );

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

    public void updateBooking(Integer bookingsId, @Valid BookingsRequest request) {
        Bookings booking = repository.findById(bookingsId)
                .orElseThrow(() -> new EntityNotFoundException(
                        format("No booking found with the booking ID:: %s", bookingsId)
                ));
        mergeBooking(booking, request);
        repository.save(booking);
    }

    private void mergeBooking(Bookings booking, @Valid BookingsRequest request) {
        if (StringUtils.isNotBlank(request.customerId())){
            booking.setCustomerId(request.customerId());
        }
        if (StringUtils.isNotBlank(request.date())){
            booking.setDate(request.date());
        }
        if (StringUtils.isNotBlank(request.time())){
            booking.setTime(request.time());
        }
        if (StringUtils.isNotBlank(request.branch())){
            booking.setBranch(request.branch());
        }
        if (StringUtils.isNotBlank(request.notes())){
            booking.setNotes(request.notes());
        }
    }

    public void patchBookingStatus(Integer bookingsId, @Valid BookingsRequest request) {
        Bookings booking  = repository.findById(bookingsId)
                .orElseThrow(() -> new EntityNotFoundException(
                        format("No booking found with the booking ID:: %s", bookingsId)
                ));
        booking.setStatus(request.status());
        repository.save(booking);
    }

    public void deleteBooking(Integer bookingsId) {

        branchClient.deleteBookingBranch(bookingsId);

        repository.deleteById(bookingsId);
    }
}
