package com.yinta.crawler.pageprocesser;/**
 * Created by Administrator on 2019/1/3.
 */

import com.yinta.entity.Amazonbtg2Entity;
import com.yinta.tools.UrlUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shijiawei
 * @date 2019/1/3
 */
public class DEPageprocesser implements PageProcessor {
    Site site = Site.me().setSleepTime(5000)
            .setRetryTimes(10)
            .setSleepTime(1000)
            .setCycleRetryTimes(10)
            .setRetrySleepTime(5000)
            .setTimeOut(10000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36");

    @Override
    public void process(Page page) {
        List<Selectable> nodes =page.getHtml().xpath("//ul[@id='zg_browseRoot']//ul/ul/li/a").nodes();
        String nodePath = (String) page.getRequest().getExtra("nodePath");
        Integer nodeLevel = (Integer) page.getRequest().getExtra("nodeLevel")+1;
        List<Amazonbtg2Entity> list = new ArrayList<>();
        for (Selectable selectable : nodes) {
            Amazonbtg2Entity amazonbtg2Entity = new Amazonbtg2Entity();
            String url = selectable.xpath("//a/@href").get();
            String nodeId = UrlUtils.getPaths(url)[4];
            amazonbtg2Entity.setUrl(url);
            amazonbtg2Entity.setNodeId(nodeId);
            String name = selectable.xpath("//a/text()").get();
            amazonbtg2Entity.setNodeName(name);
            amazonbtg2Entity.setNodeLevel(nodeLevel);
            amazonbtg2Entity.setNodePath(nodePath+">"+name);
            list.add(amazonbtg2Entity);

        }
        page.putField("ware", list);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new DEPageprocesser())
//                .addPipeline(new PPieline())
                .addUrl("https://www.amazon.de/gp/bestsellers/automotive/ref=zg_bs_nav_0")
                .start();
    }
}
