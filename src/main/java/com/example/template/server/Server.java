package com.example.template.server;

import com.example.template.tool.Result;
import com.example.template.tool.ResultDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class Server {
    @Autowired
    public ResultDB resultDB;
    @Autowired
    public Result result;

    @Autowired
    JdbcTemplate jdbcTemplate;
    public Result run(){

        return select();
    }
    public ResultDB selectSql(){
        resultDB.args = new  Object[]{5};
        resultDB.sql = "select * from word_w where ID = ? ";
        return resultDB;
    }
    public Object selectRow(Map<String,Object> map){
        Object object = new Object();
        return object;
    }

    private Result select(){
        ResultDB resultDB = selectSql();
        System.out.println(resultDB.sql);
        System.out.println(JSONObject.wrap(resultDB.args));
        if (resultDB.error != null){
            result.fail(resultDB.error);
            return result;
        }
        if (resultDB.sql.startsWith("select") == false){

            int re = jdbcTemplate.update(resultDB.sql);
            if (re == 0){
                result.fail("sql执行失败");
                return result;
            }
            result.success(null);
            return result;
        }

        List<Object> array =  new ArrayList<Object>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList(resultDB.sql,resultDB.args);
        for (Map<String,Object> map : list){
            Object object = selectRow(map);
            array.add(object);
        }
        result.success(array);
        return result;
    }

}
