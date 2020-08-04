package com.example.template.tool;

import com.example.template.server.Server;
import org.springframework.dao.DataAccessException;
import org.springframework.lang.Nullable;
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

    public static Result run(@Nullable Server... args) throws DataAccessException{
        Result result = new Result();
        for (int i = 0; i < args.length; i++) {
            Server server = args[i];
            if (i == args.length - 1){
                if (server.run()){
                    result.success(server.list);
                    return result;
                }else {
                    result.fail(server.error);
                    return result;
                }

            }else {
                if (server.run() == false){
                    result.fail(server.error);
                    return result;
                }
            }
        }
        result.fail("网络失败");
        return result;
    }


}
