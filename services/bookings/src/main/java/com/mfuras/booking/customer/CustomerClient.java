package com.mfuras.booking.customer;


import com.mfuras.booking.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor

public class CustomerClient {

    @Value("${application.config.customer-url}")
    private  String customerUrl;
    private final RestTemplate restTemplate;

    public ResponseEntity<CustomerResponse> findCustomerById(String customerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<CustomerResponse> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<CustomerResponse> response = restTemplate.exchange(
                customerUrl + "/getCustomer/" + customerId,
                GET,
                entity,
                responseType
        );
        if(response.getStatusCode().isError()){
            throw new BusinessException("An error occurred while processing customer details" + response.getStatusCode());
        }

        return response;
    }
}
