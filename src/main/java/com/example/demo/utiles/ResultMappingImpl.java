package com.example.demo.utiles;

import com.example.demo.bean.Result;
import com.example.demo.bean.ResultCode;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ResultMappingImpl {


    public static Result success() {
        String time = getTime();
        int ok = ResultCode.OK.getValue();
        Result res = new Result(ok,time,null);
        return res;
    }

    public static Result success( Object data) {
        String time = getTime();
        int ok = ResultCode.OK.getValue();
        Result res = new Result(ok,time,data);
        return res;
    }

    public static Result error() {
        String time = getTime();
        int error = ResultCode.ERROR.getValue();
        Result res = new Result(error,time,null);
        return res;
    }

    public static Result error(Object data) {
        String time = getTime();
        int error = ResultCode.ERROR.getValue();
        Result res = new Result(error,time,data);
        return res;
    }

    public static Result exception() {
        String time = getTime();
        int exception = ResultCode.EXCEPTION.getValue();
        Result res = new Result(exception,time,null);
        return res;
    }

    public static Result exception(Object data) {
        String time = getTime();
        int exception = ResultCode.EXCEPTION.getValue();
        Result res = new Result(exception,time,data);
        return res;
    }


    public static String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,0);
        String time = simpleDateFormat.format(instance.getTime());
        return  time;
    }


}
