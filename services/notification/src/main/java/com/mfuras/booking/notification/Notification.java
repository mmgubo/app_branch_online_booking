package com.mfuras.booking.notification;


import com.mfuras.booking.kafka.bookings.BookingsConfirmation;
import com.mfuras.booking.kafka.branch.BranchConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private BranchConfirmation branchConfirmation;
    private BookingsConfirmation bookingsConfirmation;

}
