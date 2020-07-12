package com.example.template.tool;

import org.springframework.stereotype.Service;

@Service
public class Result {
    public int code;
    public String message;
    public Object data;
    public void success(Object obj){
        data = obj;
        code = 1;
        message = null;
    }
    public void fail(String error){
        message = error;
        code = 100;
        data = null;
    }

}
