package com.example.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


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
        logger.info("这是一个分支冲突测试");
        logger.info("===结束一次调度3===");
    }
}
