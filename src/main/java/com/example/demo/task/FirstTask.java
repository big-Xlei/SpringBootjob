package com.example.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class FirstTask {
    Logger logger = LoggerFactory.getLogger(FirstTask.class);
    @Scheduled(fixedRate = 1000*3)
    public void test(){
        logger.info("===开始调度程序===");




        logger.info("===结束一次调度===");
    }

}
