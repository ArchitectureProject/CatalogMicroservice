package com.efrei.catalogmicroservice.provider.bowling;

import com.efrei.catalogmicroservice.config.Properties;
import com.efrei.catalogmicroservice.exception.custom.BowlingParkMicroserviceException;
import com.efrei.catalogmicroservice.model.dto.Localisation;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BowlingParkProviderImpl implements BowlingParkProvider {
    private final RestTemplate restTemplate;
    private final Properties properties;

    public BowlingParkProviderImpl(RestTemplate restTemplate, Properties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public Localisation getBowlingParkAlleyFromQrCode(String bearerToken, String qrCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", bearerToken);

        HttpEntity<List<String>> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Localisation> responseEntity = restTemplate.exchange(
                    properties.getBowlingMicroserviceBaseUrl() + BowlingParkEndpoints.getBowlingParkAlleyByQrCode + qrCode,
                    HttpMethod.GET,
                    requestEntity,
                    Localisation.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            throw new BowlingParkMicroserviceException("BowlingPark microservice /BowlingPark/fromQrCode/ sent back an error", e);
        }
    }
}
