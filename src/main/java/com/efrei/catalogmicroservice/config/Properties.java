package com.efrei.catalogmicroservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "conf")
public class Properties {

    private String jwkUrl;

    private String bowlingMicroserviceBaseUrl;

    public String getJwkUrl() {
        return jwkUrl;
    }

    public void setJwkUrl(String jwkUrl) {
        this.jwkUrl = jwkUrl;
    }

    public String getBowlingMicroserviceBaseUrl() {
        return bowlingMicroserviceBaseUrl;
    }

    public void setBowlingMicroserviceBaseUrl(String bowlingMicroserviceBaseUrl) {
        this.bowlingMicroserviceBaseUrl = bowlingMicroserviceBaseUrl;
    }
}
