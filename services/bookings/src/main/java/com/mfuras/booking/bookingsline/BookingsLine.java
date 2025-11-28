package com.mfuras.booking.bookingsline;

import com.mfuras.booking.bookings.Bookings;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
public class BookingsLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Bookings product;
    private Integer productId;
}
