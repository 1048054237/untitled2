package com.yinta.tools;

import com.squareup.okhttp.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/25.
 *
 * @author 史佳伟
 */
public class OkhttpUtils {
    private static final OkHttpClient MOKHTTPCLIENT = new OkHttpClient();
    private Request requests = null;


    static {
        MOKHTTPCLIENT.setConnectTimeout(5000, TimeUnit.SECONDS);
        MOKHTTPCLIENT.setReadTimeout(5000, TimeUnit.SECONDS);
        MOKHTTPCLIENT.setWriteTimeout(5000, TimeUnit.SECONDS);
    }

    public OkhttpUtils(Request request) {
        this.requests = request;
    }


    public static Response execute(Request request, String ip, int port) throws IOException {
        final int localHost = 100;
        if (port == localHost) {
            return MOKHTTPCLIENT.newCall(request).execute();
        } else {
            return MOKHTTPCLIENT.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port))).newCall(request).execute();
        }
    }

    public static Response execute(Request request) throws IOException {
        Call call = MOKHTTPCLIENT.newCall(request);
        return call.execute();
    }


    public static void enqueue(Request request, Callback responseCallback) {
        MOKHTTPCLIENT.newCall(request).enqueue(responseCallback);
    }

    static int serversLoadTimes;

    public static void enqueue(Request request) {
        serversLoadTimes = 0;
        final int serversMaxTimes = 3;
        MOKHTTPCLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                if (e.getCause().equals(SocketTimeoutException.class) && serversLoadTimes < serversMaxTimes) {
                    serversLoadTimes++;
                    MOKHTTPCLIENT.newCall(request).enqueue(this);
                } else {
                    e.printStackTrace();
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println("This reponse is Successful");
                } else {
                    System.out.println("This is OkhttpUtils,and it has a Exeception");
                }
            }
        });
    }

    public static String getStringFromServer(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            String responseUrl = response.body().string();
            return responseUrl;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    private static final String CHARSET_NAME = "UTF-8";


    public static String formatParams(List<BasicNameValuePair> params) {
        return URLEncodedUtils.format(params, CHARSET_NAME);
    }

    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params) {
        return url + "?" + formatParams(params);
    }

    public static String attachHttpGetParam(String url, String name, String value) {
        return url + "?" + name + "=" + value;
    }

}
