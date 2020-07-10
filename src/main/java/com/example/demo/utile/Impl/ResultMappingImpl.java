package com.example.demo.utile.Impl;

import com.example.demo.bean.Result;
import com.example.demo.bean.ResultCode;
import com.example.demo.utile.ResultMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ResultMappingImpl implements ResultMapping {


    @Override
    public Result success() {
        String time = getTime();
        int ok = ResultCode.OK.getValue();
        Result res = new Result(ok,time,null);
        return res;
    }

    @Override
    public Result success( Object data) {
        String time = getTime();
        int ok = ResultCode.OK.getValue();
        Result res = new Result(ok,time,data);
        return res;
    }

    @Override
    public Result error() {
        String time = getTime();
        int error = ResultCode.ERROR.getValue();
        Result res = new Result(error,time,null);
        return res;
    }

    @Override
    public Result error(Object data) {
        String time = getTime();
        int error = ResultCode.ERROR.getValue();
        Result res = new Result(error,time,data);
        return res;
    }

    @Override
    public Result exception() {
        String time = getTime();
        int exception = ResultCode.EXCEPTION.getValue();
        Result res = new Result(exception,time,null);
        return res;
    }

    @Override
    public Result exception(Object data) {
        String time = getTime();
        int exception = ResultCode.EXCEPTION.getValue();
        Result res = new Result(exception,time,data);
        return res;
    }


    public static String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH24:min:ss");
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,0);
        String time = simpleDateFormat.format(instance.getTime());
        return  time;
    }


}
