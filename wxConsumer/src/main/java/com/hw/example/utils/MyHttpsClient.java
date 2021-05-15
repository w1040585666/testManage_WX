package com.hw.example.utils;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author :WXIN
 */
public class MyHttpsClient {

    public static String sendHttpsPost(String url,Object param) throws  Exception {
             CloseableHttpClient httpClient = createHttpClient();
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader(HTTP.CONTENT_TYPE, "application/json");
            StringEntity se = new StringEntity(JSONUtils.toJSONString(param),"UTF-8");
            httppost.setEntity(se);
            CloseableHttpResponse httpResponse = httpClient.execute(httppost);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            return  result;
    }

    private static  CloseableHttpClient createHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();

        SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(sslcontext, null, null,
                new NoopHostnameVerifier());

        return HttpClients.custom().setSSLSocketFactory(sslSf).build();
    }


}
