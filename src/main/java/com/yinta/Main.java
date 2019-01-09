package com.yinta;/**
 * Created by Administrator on 2019/1/3.
 */

import com.yinta.test.TestComponent;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author shijiawei
 * @date 2019/1/3
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
            TestComponent bean = run.getBean(TestComponent.class);
            bean.start();
//            DELancher bean = run.getBean(DELancher.class);
//            bean.stratCrawler();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
