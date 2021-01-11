package com.example.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
@EnableScheduling
public class FirstTask {
    Logger logger = LoggerFactory.getLogger(FirstTask.class);

    @Scheduled(fixedRate = 1000*3)
    @Async
    public void starttask(){
        logger.info("===开始调度程序===");
        logger.info("===结束一次调度===");
    }

    @Scheduled(fixedRate = 1000*3)
    @Async
    public void starttask2(){
        logger.info("===开始调度程序2===");
        logger.info("===结束一次调度2===");
    }

    @Scheduled(fixedRate = 1000*3)
    @Async
    public void starttask3(){
        logger.info("===开始调度程序3===");
        logger.info("===结束一次调度3===");
    }
}
