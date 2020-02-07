package com.chatelain.deliverbackend.utils.wx;

import com.chatelain.deliverbackend.exception.InternalHttpException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;

@Component
public class AESDecoder {


    public DecodedMobileJson decode(String sessionKey, String iv, String encryptedData) throws Exception{
        BASE64Decoder decoder = new BASE64Decoder();

        SecretKeySpec key = new SecretKeySpec(decoder.decodeBuffer(sessionKey), "AES");

        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(decoder.decodeBuffer(iv)));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, params);
        byte[] result = cipher.doFinal(decoder.decodeBuffer(encryptedData));

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result, DecodedMobileJson.class);
    }

}
