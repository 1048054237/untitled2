package com.yinta.dao;/**
 * Created by Administrator on 2019/1/3.
 */

import com.yinta.entity.Amazonbtg2Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 史佳伟
 * @date 2019/1/3
 */
public interface AmazonbtgDao extends JpaRepository<Amazonbtg2Entity, Integer> {
    //   @Query("from Amazonbtg2Entity  where nodeLevel= :nodeLevel")
    List<Amazonbtg2Entity> findAllByNodeLevel(/*@PathVariable("nodeLevel") */Integer nodeLevel);
}
