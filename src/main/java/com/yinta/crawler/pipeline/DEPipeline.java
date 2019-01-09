package com.yinta.crawler.pipeline;/**
 * Created by Administrator on 2019/1/3.
 */

import com.yinta.dao.AmazonbtgDao;
import com.yinta.entity.Amazonbtg2Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @Author shijiawei
 * @date 2019/1/3
 */
@Service
public class DEPipeline implements Pipeline {
    @Autowired
    private AmazonbtgDao dao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Amazonbtg2Entity> ware = (List<Amazonbtg2Entity>) resultItems.getAll().get("ware");
        dao.saveAll(ware);
    }
}
