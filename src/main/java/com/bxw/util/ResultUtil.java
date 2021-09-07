package com.bxw.util;

public class ResultUtil {
    private int status; // 状态码
    private String message; // 提示信息
    private Object date; // 数据

    public ResultUtil() {
    }

    public ResultUtil(int status, String message, Object date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}

