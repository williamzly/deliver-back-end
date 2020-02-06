package com.chatelain.deliverbackend.utils.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("wechat")
@PropertySource("classpath:/wechat.properties")
@Data
public class WXConfig {

    public String appId;

    public String appSecret;
}
