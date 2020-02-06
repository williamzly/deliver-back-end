package com.chatelain.deliverbackend.utils.http;

import com.chatelain.deliverbackend.exception.InternalHttpException;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Map.Entry;

public class HttpClientUtils {

    private static final String ENCODING = "UTF-8";

    private static final int CONNECT_TIMEOUT = 2000;

    private static final int SOCKET_TIMEOUT = 2000;

    private static RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();

    public static HttpClientResult doGet(String url) {
        return doGet(url, null, null);
    }

    public static HttpClientResult doGet(String url, Map<String, String> params) {
        return doGet(url, null, params);
    }

    public static HttpClientResult doGet(String url, Map<String, String> headers, Map<String, String> params) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault();) {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue());
                }
            }

            HttpGet httpGet = new HttpGet(uriBuilder.build());

            httpGet.setConfig(requestConfig);

            packageHeader(headers, httpGet);

            return getHttpClientResult(httpClient, httpGet);
        } catch (IOException | URISyntaxException e) {
            throw new InternalHttpException(e.getMessage());
        }
    }

    public static HttpClientResult doPost(String url) {
        return doPost(url, null, null);
    }

    public static HttpClientResult doPost(String url, Map<String, String> body) {
        return doPost(url, null, body);
    }

    public static HttpClientResult doPost(String url, Map<String, String> headers, Map<String, String> body) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault();) {

            // 创建http对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
			/*
			httpPost.setHeader("Cookie", "");
			httpPost.setHeader("Connection", "keep-alive");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
			httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
			*/
            packageHeader(headers, httpPost);

            packageParam(body, httpPost);

            return getHttpClientResult(httpClient, httpPost);
        } catch (IOException e) {
           throw new InternalHttpException(e.getMessage());
        }
    }

    public static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod) {
        if (params != null) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    public static void packageParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod)
            throws UnsupportedEncodingException {
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<>();
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
        }
    }

    public static HttpClientResult getHttpClientResult(CloseableHttpClient httpClient, HttpRequestBase httpMethod) throws IOException {
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);) {
            if (httpResponse != null && httpResponse.getStatusLine() != null) {
                String content = "";
                if (httpResponse.getEntity() != null) {
                    content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
                }
                return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), content);
            }
            return new HttpClientResult(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

}