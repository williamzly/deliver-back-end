package com.chatelain.deliverbackend.utils.wx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AESDecoderTest {

    @Test
    public void testDecode() throws Exception {

        String iv = "Gh1Y32N+W4RBg4J7gFICqg==";

        String encryptedData = "n7V/x908Q1ozfFvSdD4q3ZbsG9VuCxpABoa0YZWDBJj4y0p/1oSzrnUxWteMQ8Q3ot1dCcKLea8Ofn2sYQa4ThTjx8LPdHXh5JmVK8urf71Iix0FZuG18J8uM33ZwSMFzM7q5gFpbSaNUhvEK4+AlUu/SO7GR+59kiODPHd/ljE10QNxEFuZ8ki9tmAMfaJkYr3gMHduOM4i7w899u68DA==";
        String sessionKey = "3mxAzzSfV4/JGLTf7PcLSg==";
        AESDecoder aesDecoder = new AESDecoder();

        DecodedMobileJson mobileJson = aesDecoder.decode(sessionKey, iv, encryptedData);

        assertEquals("18674784930", mobileJson.getPhoneNumber());

    }

}