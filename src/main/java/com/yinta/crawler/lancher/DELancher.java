package com.yinta.crawler.lancher;/**
 * Created by Administrator on 2019/1/3.
 */

import com.yinta.crawler.pageprocesser.DEPageprocesser;
import com.yinta.crawler.pipeline.DEPipeline;
import com.yinta.dao.AmazonbtgDao;
import com.yinta.entity.Amazonbtg2Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * @Author shijiawei
 * @date 2019/1/3
 */
@Component
public class DELancher {

    @Autowired
    private DEPipeline pipeline;
    @Autowired
    private AmazonbtgDao dao;

    public void stratCrawler() {
        List<Amazonbtg2Entity> allByNodeLevel = dao.findAllByNodeLevel(9);
        Request[] request = getRequest(allByNodeLevel);
        Request request1 = new Request();
        request1.setUrl("https://www.amazon.de/gp/bestsellers/automotive/ref=zg_bs_unv_auto_1_79923031_1")
                .putExtra("nodeLevel", 1)
                .putExtra("nodePath", " Auto & Motorrad");
        Spider.create(new DEPageprocesser())

                .addRequest(request1)
                .addRequest(request)
//                .addPipeline(pipeline)
                .start();
    }

    public Request[] getRequest(List<Amazonbtg2Entity> list) {
        Request[] requests = new Request[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Amazonbtg2Entity amazonbtg2Entity = list.get(i);
            Request request = new Request();
            request.setUrl(amazonbtg2Entity.getUrl());
            request.putExtra("nodeLevel", amazonbtg2Entity.getNodeLevel());
            request.putExtra("nodePath", amazonbtg2Entity.getNodePath());
            requests[i] = request;

        }
        return requests;

    }

}
