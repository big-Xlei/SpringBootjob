package com.example.demo.bean;

import org.junit.Test;

public enum ResultCode {
    OK(0),
    ERROR(1),
    EXCEPTION(2);

    private final int value;

    ResultCode(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}


