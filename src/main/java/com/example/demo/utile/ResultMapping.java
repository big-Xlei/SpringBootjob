package com.example.demo.utile;

import com.example.demo.bean.Result;

public interface ResultMapping {
    public Result success();
    public Result success(Object data);


    public Result error();
    public Result error(Object data);

    public Result exception();
    public Result exception(Object data);

}
