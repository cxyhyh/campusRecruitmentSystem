package com.zbdx.xyzp.util;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code;
    private String message;
    private Object Data;

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");;
        result.setData(data);
        return result;
    }

    public static Result success(int code , String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result success(int code , String message , Object Data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(Data);
        return result;
    }

    public static Result success(String message){
        Result result = new Result();
        result.setMessage(message);
        return result;
    }

    public static Result fail(Object data){
        Result result = new Result();
        result.setCode(400);
        result.setMessage("fail");
        result.setData(data);
        return result;
    }

    public static Result fail(String message){
        Result result = new Result();
        result.setMessage(message);
        return result;
    }

    public static Result fail(int code , String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
