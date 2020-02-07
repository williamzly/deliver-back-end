package com.chatelain.deliverbackend.utils.wx;

import lombok.Data;

import java.util.Map;

@Data
public class DecodedMobileJson {

    private String phoneNumber;

    private String purePhoneNumber;

    private String countryCode;

    private Map<String, String> watermark;

}
