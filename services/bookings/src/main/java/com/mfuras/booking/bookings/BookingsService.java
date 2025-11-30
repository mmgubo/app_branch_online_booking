package com.mfuras.booking.bookings;

import com.mfuras.booking.bookingsline.BookingLineRequest;
import com.mfuras.booking.bookingsline.BookingsLineService;
import com.mfuras.booking.branch.BranchClient;
import com.mfuras.booking.branch.BranchRequest;
import com.mfuras.booking.customer.CustomerClient;
import com.mfuras.booking.exception.BusinessException;
import com.mfuras.booking.kafka.BookingsConfirmation;
import com.mfuras.booking.kafka.BookingsProducer;
import com.mfuras.booking.product.ProductClient;
import com.mfuras.booking.product.SelectedProductRequest;
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
    private final ProductClient productClient;
    private final BookingsMapper mapper;
    private final BookingsLineService bookingsLineService;
    private final BookingsProducer bookingsProducer;
    private final BranchClient branchClient;

    public Integer createBooking(@Valid BookingsRequest request) {
        //check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create booking:: No customer exist with the provided ID:: " + request.customerId()));

        var selectedProducts = this.productClient.selectedProducts(request.products());

        var bookings = this.repository.save(mapper.toBookings(request));

        for (SelectedProductRequest bookingsRequest : request.products()){
            bookingsLineService.saveBookingLine(
                    new BookingLineRequest(
                            null,
                            bookings.getId(),
                            bookingsRequest.productId()

                    )
            );
        }

        // branch process
        var branchRequest = new BranchRequest(
                bookings.getId(),
                bookings.getReference(),
                customer
        );
        branchClient.requestBookingBranch(branchRequest);

        //send the booking confirmation --> notification-ms (kafka)
        bookingsProducer.sendBookingsConfirmation(
                new BookingsConfirmation(
                        request.reference(),
                        request.bookingsMethod(),
                        customer,
                        selectedProducts
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
}
