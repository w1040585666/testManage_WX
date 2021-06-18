package com.hw.example.utils;

import java.io.Serializable;
import java.util.Date;

/**
 * 微服务端统一返回格式
 * @author wang xin.
 * @version 1.0
 * @date 2021/01/11 14:09.
 * @Copyright：2021 汉王智远科技有限公司 All rights reserved.
 */
public class RequestJson implements Serializable {

    private static final long serialVersionUID = 6447507192815970954L;

    private boolean success;
    
    private int code;

    private String msg = "";

    private transient Object obj;

    private Date date;

    public RequestJson() {
        super();
        this.success = false;
    }

    /**
     * 成功返回格式
     * @param result    RequestJson
     * @param obj       返回的具体参数
     * @param msg       操作提示语
     * @return
     */
    public static RequestJson successResult(RequestJson result, Object obj, String msg) {
        result.setSuccess(true);
        result.setObj(obj);
        result.setMsg(msg);
        return result;
    }
    /**
     * 成功返回格式
     * @param result    RequestJson
     * @param msg       操作提示语
     * @return
     */
    public static RequestJson successResult(RequestJson result, String msg) {
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败返回格式
     * @param result    RequestJson
     * @param msg       操作提示语
     * @return
     */
    public static RequestJson failuerResult(RequestJson result, String msg) {
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    /**
     * 异常返回格式
     * @param result    RequestJson
     * @param msg       操作提示语
     * @return
     */
    public static RequestJson errorResult(RequestJson result, String msg) {
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "RequestJson [success=" + success + ", msg=" + msg + ", obj=" + obj + "]";
    }
}
