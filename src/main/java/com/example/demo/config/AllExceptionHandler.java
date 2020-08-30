package com.example.demo.config;

import com.example.demo.bean.Result;
import com.example.demo.utiles.ResultMappingImpl;
import com.example.demo.utiles.UsersException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AllExceptionHandler {

    //捕获所有的异常
    @ExceptionHandler(value = Exception.class)
    public <E>Result handler(Exception exception){
        exception.printStackTrace();
        if(exception instanceof UsersException){

            log.error(exception.getMessage()+"=======================");
            return ResultMappingImpl.error(exception.getMessage());

        }

        return ResultMappingImpl.error("后台接口异常！，请联系开发人员");
    }
}
