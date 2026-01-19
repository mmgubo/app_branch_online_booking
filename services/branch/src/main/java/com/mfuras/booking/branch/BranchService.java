package com.mfuras.booking.branch;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository repository;
    private final BranchMapper mapper;


    public Integer createBranch(BranchRequest request) {
        var branch = repository.save(mapper.toBranch(request));

        return branch.getId();
    }
}
