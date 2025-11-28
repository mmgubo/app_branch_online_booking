package com.mfuras.booking.product;

import com.mfuras.booking.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("{${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;


    public List<SelectedProductResponse> selectedProducts(List<SelectedProductRequest> requestBody){
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<List<SelectedProductRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<SelectedProductResponse>> responseType =
                new ParameterizedTypeReference<List<SelectedProductResponse>>() {};
        ResponseEntity<List<SelectedProductResponse>> responseEntity = restTemplate.exchange(
              productUrl + "/selected",
                POST,
                requestEntity,
                responseType

        );
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("An error occurred while calling the product service: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
