package com.example.demo.task;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Component
@EnableScheduling
public class FirstTask {
    Logger logger = LoggerFactory.getLogger(FirstTask.class);

    @Scheduled(cron = "*/5 * * * * ?")
    @Async
    public void starttask(){
        logger.info("===开始调度程序===");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH24:mm:ss");
        System.out.println("11111"+simpleDateFormat.format(new Date()));
        logger.info("===结束一次调度===");
    }

    @Scheduled(cron = "*/10 * * * * ?")
    @Async
    public void starttask2(){
        logger.info("===开始调度程序2===");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH24:mm:ss");
        System.out.println("22222"+simpleDateFormat.format(new Date()));
        logger.info("===结束一次调度2===");
    }

    @Scheduled(fixedRate = 1000*6)
    @Async
    public void starttask3(){
        logger.info("===开始调度程序3===");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH24:mm:ss");
        System.out.println("333333"+simpleDateFormat.format(new Date()));
        logger.info("这是一个分支冲突测试,但是这是test");
        logger.info("===结束一次调度3===");
    }


    @Test
    public void TestJsonanaly(){
        String json = "{ \"result\": 0, \"message\": \"success\", \"total\": 1, \"pageCount\": 1, \"items\": [ { \"routeId\": 1001000156, \"routeName\": \"175 路\", \"buses\": { \"1001021140\": { \"deviceNo\": \"50079675\", \"busNo\": \"7-9675\", \"plateNo\": \"浙 A4H291\", \"busId\": 1001021140, \"lng\": 120.2166989, \"lat\": 30.19640599, \"routeId\": 1001000156, \"routeName\": \"175 路\", \"direction\": 0, \"updateTime\": \"2019-09-19 11:18:46\" }, \"1001021227\": { \"deviceNo\": \"50079690\", \"busNo\": \"7-9690\", \"plateNo\": \"浙 A4H046\", \"busId\": 1001021227, \"lng\": 120.21107115, \"lat\": 30.18311408, \"routeId\": 1001000156, \"routeName\": \"175 路\", \"direction\": 0, \"updateTime\": \"2019-09-19 11:21:10\" }, \"1001021835\": { \"deviceNo\": \"50079597\", \"busNo\": \"7-9597\", \"plateNo\": \"浙 A4H209\", \"busId\": 1001021835, \"lng\": 120.21446623, \"lat\": 30.19824404, \"routeId\": 1001000156, \"routeName\": \"175 路\", \"direction\": 1, \"updateTime\": \"2019-09-19 11:21:13\" }, \"1001021237\": { \"deviceNo\": \"50079700\", \"busNo\": \"7-9700\", \"plateNo\": \"浙 A4H056\", \"busId\": 1001021237, \"lng\": 120.16195319, \"lat\": 30.15271319, \"routeId\": 1001000156, \"routeName\": \"175 路\", \"direction\": 0, \"updateTime\": \"2019-09-19 11:18:46\" }, \"1001021291\": { \"deviceNo\": \"50079701\", \"busNo\": \"7-9701\", \"plateNo\": \"浙 A4H057\", \"busId\": 1001021291, \"lng\": 120.21666198, \"lat\": 30.19629703, \"routeId\": 1001000156, \"routeName\": \"175 路\", \"direction\": 0, \"updateTime\": \"2019-09-19 11:21:14\" } } } ] }";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map<String,Object> busesmap = jsonObject.getJSONArray("items").getJSONObject(0).getObject("buses", Map.class);
        for (Map.Entry<String, Object> stringObjectEntry : busesmap.entrySet()) {
            System.out.println(stringObjectEntry.getKey() + "===" + stringObjectEntry.getValue());
        }
    }
    @Test
    public void TestSti(){
        // 可以用 StringBuilder 这个类，里面有一个接口replace，如下
        StringBuilder sb = new StringBuilder("abcbcd");
        sb.replace(sb.length()-2, sb.length()-1, "测试是否替换指定的第二个元素");
        System.out.println(sb.toString());
    }
}
