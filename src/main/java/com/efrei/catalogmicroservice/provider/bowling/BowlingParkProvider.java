package com.efrei.catalogmicroservice.provider.bowling;

import com.efrei.catalogmicroservice.model.dto.Localisation;

import java.util.List;

public interface BowlingParkProvider {

    Localisation getBowlingParkAlleyFromQrCode(String bearerToken, String qrCode);
}
