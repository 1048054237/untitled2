package com.yinta.test;/**
 * Created by Administrator on 2019/1/9.
 */

import cn.hutool.json.JSONObject;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yinta.tools.OkhttpUtils;

import java.io.IOException;

/**
 * @Author shijiawei
 * @date 2019/1/9
 */
public class TestThread implements Runnable {

    private JSONObject jsonObject = null;
    //从json里面获取所需要的连接，然后进行请求的发送


    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        String url = jsonObject.getStr("url");
        String str = jsonObject.getStr("User-Agent");
        Request build = new Request.Builder().get().url(url).addHeader("User-Agent", str).build();
        Response execute = null;
        try {
            execute = OkhttpUtils.execute(build);
            if (execute != null && execute.isSuccessful()) {
                System.out.println("suceess");
            } else {
                System.out.println("failed");
            }
        } catch (IOException e) {
            System.out.println("has Exception");
//            e.printStackTrace();
        } finally {
            if (execute != null) {
                try {
                    execute.body().close();
                } catch (IOException e) {
                    System.out.println("this has Exception");
//                    e.printStackTrace();
                }
            }
        }


    }
}
