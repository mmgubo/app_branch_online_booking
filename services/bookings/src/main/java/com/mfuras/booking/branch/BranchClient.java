package com.mfuras.booking.branch;

import com.mfuras.booking.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor

public class BranchClient {

    @Value("${application.config.branch-url}")
    private  String branchUrl;
    private final RestTemplate restTemplate;

    public ResponseEntity<Integer> requestBookingBranch(BranchRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<BranchRequest> requestEntity = new HttpEntity<>(request, headers);
        ParameterizedTypeReference<Integer> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<Integer> response = restTemplate.exchange(
                branchUrl + "/createBranch",
                POST,
                requestEntity,
                responseType
        );
        if(response.getStatusCode().isError()){
            throw new BusinessException("An error occurred while processing branch details" + response.getStatusCode());
        }
        return response;
    }
}
