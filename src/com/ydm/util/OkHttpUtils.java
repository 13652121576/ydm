package com.ydm.util;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {

    private static final int CONNECT_TIMEOUT = 5;
    private static final int READ_TIMEOUT = 60;

    public static final MediaType JSON_UTF8 = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient.Builder getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder;
    }

    public static String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = getOkHttpClient().build().newCall(request).execute();
        return response.body().string();
    }

    public static String run(String url, Map<String, String> params) throws IOException {

        FormBody.Builder builder = new FormBody.Builder();
        if (params == null) {
            params = Maps.newHashMap();
        }
        for (Entry<String, String> e : params.entrySet()) {
            builder.add(e.getKey(), e.getValue());
        }

        Request request = new Request.Builder().url(url).post(builder.build()).build();
        Response response = getOkHttpClient().build().newCall(request).execute();
        return response.body().string();
    }

    public static String runSsl(String url, Map<String, String> params) throws Exception {

        FormBody.Builder builder = new FormBody.Builder();
        if (params == null) {
            params = Maps.newHashMap();
        }
        for (Entry<String, String> e : params.entrySet()) {
            builder.add(e.getKey(), e.getValue());
        }

        Request request = new Request.Builder().url(url).post(builder.build()).build();
        final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }
        } };

        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        Response response = getOkHttpClient().sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                .build().newCall(request).execute();
        return response.body().string();
    }
    /**
     * 获取请求url地址
     * @param request
     * @return
     */
    public static String getUrl(HttpServletRequest request){
    	String contextPath=request.getContextPath();//应用名称 服务器部署的都没有应用名称
    	String requestUrl="";
    	if(StringUtils.isNotBlank(contextPath)) {
    		requestUrl = request.getScheme() //当前链接使用的协议
	    		    +"://" + request.getServerName()//服务器地址 
	    		    + ":" + request.getServerPort() //端口号 
	    		    + request.getContextPath(); //应用名称
    	}else {
    		requestUrl = request.getScheme() //当前链接使用的协议
        		    +"://" + request.getServerName();//服务器地址 
    	}
		return requestUrl;
    }

}
