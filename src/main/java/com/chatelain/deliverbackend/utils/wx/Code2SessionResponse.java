package com.chatelain.deliverbackend.utils.wx;

import lombok.Data;

@Data
public class Code2SessionResponse {

    private String openid;

    private String session_key;

    private String unionid;

    private Integer errcode;

    private String errmsg;
}
