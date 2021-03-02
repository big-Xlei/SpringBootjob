package com.example.demo.common.response;

/**
*@Description: 统一响应编码
*@Author: xionglei
*@Date: 2021/2/24 10:29
*
*@return:
*/
public enum ResponseCode {
    SUCCESS("success", 200, "成功"),

    TOKEN_ERROR(300, "token过期或错误"),
    SYSTEM_ERROR(500, "系统异常"),
    MISSING_REQUEST_PARAM_ERROR(501, "缺少必须的参数"),
    UNAUTHORIZED(502, "权限不足"),
    PARAM_ERROR(503, "参数校验错误"),
    SERVICE_ERROR(504,"服务异常");

    private final String success;
    private final Integer code;
    private final String msg;

    private static final String DEFAULT_MESSAGE = "系统异常";
    ResponseCode(String success,Integer code, String message) {
        this.success=success;
        this.code = code;
        this.msg = message;
    }
    ResponseCode(Integer code, String message) {
        this.success="fail";
        this.code = code;
        this.msg = message;
    }


    public Integer getCode() {
        if (code != null) {
            return code;
        } else {
            return 500;
        }
    }

    public String getMsg() {
        if (msg != null) {
            return msg;
        } else {
            return DEFAULT_MESSAGE;
        }
    }

    public String getSuccess() {
        return success;
    }

}
