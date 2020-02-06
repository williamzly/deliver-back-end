package com.chatelain.deliverbackend.security;

public class Constant {

    public static final String JWT_HEAD = "Authorization";

    public static final String JWT_HEAD_START = "zhurong-deliver ";

    public static final String JWT_SECRET = "chatelain";

    public static final String LOGIN_PATH = "/login";

    public static final String[] PERMITTED_API_PATH = new String[]{

    };

    public static final String[] PERMITTED_PAGE_PATH = new String[]{
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };
}
