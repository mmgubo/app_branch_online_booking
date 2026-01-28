package com.mfuras.booking.branch;


import com.mfuras.booking.notification.BranchNotificationRequest;
import com.mfuras.booking.notification.NotificationProducer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository repository;
    private final BranchMapper mapper;
    private final NotificationProducer notificationProducer;


    public Integer createBranch(BranchRequest request) {
        var branch = repository.save(mapper.toBranch(request));

        //Exclude sending out notifications in this release
        /** this.notificationProducer.sendNotification(
                new BranchNotificationRequest(
                        request.bookingReference(),
                        request.customer().name(),
                        request.customer().email()
                )
        );**/

        return branch.getId();
    }

    public void deleteBranch(Integer bookingsId) {

        repository.deleteById(bookingsId);
    }
}
