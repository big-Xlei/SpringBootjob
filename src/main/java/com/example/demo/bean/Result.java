package com.example.demo.bean;

public class Result {
    private int status;
    private String time;
    private Object data;

    public Result() {
    }
    public Result(int status,String time,Object data){
        this.status = status;
        this.time = time;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public Object getdata() {
        return data;
    }

    public void setdata(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", time='" + time + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
