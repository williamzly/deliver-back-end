package com.chatelain.deliverbackend.utils.wx;

import com.chatelain.deliverbackend.exception.ExternalHttpException;
import com.chatelain.deliverbackend.utils.http.HttpClientResult;
import com.chatelain.deliverbackend.utils.http.HttpClientUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.chatelain.deliverbackend.utils.wx.Constant.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class WXRequestTool {

    @Autowired
    private WXConfig wxConfig;

    public Code2SessionResponse codeToSession(String code) {

        Map<String, String> params = new HashMap<>();
        params.put("appid", wxConfig.appId);
        params.put("secret", wxConfig.appSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        HttpClientResult result = HttpClientUtils.doGet(URL_CODE_TO_SESSION, params);
        if(result.getCode() == HttpStatus.SC_OK) {
            ObjectMapper objectMapper = new ObjectMapper().disable(FAIL_ON_UNKNOWN_PROPERTIES);
            try {
                Code2SessionResponse code2SessionResponse = objectMapper.readValue(result.getContent(), Code2SessionResponse.class);
                if(Objects.isNull(code2SessionResponse.getErrcode()) ||  SUCCESS_CODE.equals(code2SessionResponse.getErrcode())) {
                    return code2SessionResponse;
                }else {
                    throw new ExternalHttpException("errcode is not success, errmsg: " + code2SessionResponse.getErrmsg());
                }
            } catch (JsonProcessingException e) {
                throw new ExternalHttpException("parse response content fail");
            }
        }else {
            throw new ExternalHttpException("response status is not OK");
        }

    }

}
