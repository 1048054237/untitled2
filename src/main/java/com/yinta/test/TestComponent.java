package com.yinta.test;/**
 * Created by Administrator on 2019/1/9.
 */

import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author shijiawei
 * @date 2019/1/9
 */
@Component
public class TestComponent {
    //网站最低的编号
    private Integer minIndex = 1;
    //网站的默认名称
    private String defaultName = "site_";
    //网站最大的编号
    private Integer maxIndex = 1;
    //线程池
    private ThreadPoolExecutor poolExecutor = null;
    //keepAliveTime
    private long keepAliveTime = 0;
    //线程数量跟网站数量的换算比
    private Integer en = 5;
    //需要测试的网站的host
    private String host = "http://66.42.59.225/";
    //详情页的测试连接
    private String detailsUrl = "/public_html/en/arizona-cardinals-c-5507/custom-jersey-c-5508/nike-arizona-cardinals-men-s-limited-black-ized-alternate-jersey-p-602";
    //列表页测试连接
    private String listUrl = "/public_html/en/arizona-cardinals-c-5507/custom-jersey-c-5508/";

    {
        Integer threadNum = maxIndex * en;
        poolExecutor = new ThreadPoolExecutor(threadNum, threadNum, keepAliveTime,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }


    public void start() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        for (; minIndex <= maxIndex; minIndex++) {
            for (int i = 0; i < en; i++) {
                jsonObject.put("url", host + defaultName + minIndex +detailsUrl);
//                jsonObject.put("url", "https://www.oedro.com");
                TestThread testThread = new TestThread();
                testThread.setJsonObject(jsonObject);
                poolExecutor.execute(testThread);
            }

        }

    }
}
