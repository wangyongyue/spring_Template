package com.example.template.server;

import com.example.template.beans.ServerBean;
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
    public String getType(){
        return "POST";
    }
    public Result run(){
        if (getType() == "POST" || getType() == "GET"){
            return select();
        }
        return update();
    }
    public ResultDB selectSql(){
        resultDB.args = new  Object[]{5};
        resultDB.sql = "select * from word_w where ID = ? ";
        return resultDB;
    }
    public ServerBean selectRow(Map<String,Object> map){
        ServerBean b = new ServerBean();
        b.name = "sdfsds";
        return b;
    }
    private Result update(){
        ResultDB resultDB = selectSql();
        System.out.println(resultDB.sql);
        if (resultDB.error != null){
            result.fail(resultDB.error);
            return result;
        }
        int re = jdbcTemplate.update(resultDB.sql);
        if (re != 0){
            result.fail("sql执行失败");
            return result;
        }
        result.success(null);
        return result;
    }
    private Result select(){
        ResultDB resultDB = selectSql();
        System.out.println(resultDB.sql);
        System.out.println(JSONObject.wrap(resultDB.args));
        if (resultDB.error != null){
            result.fail(resultDB.error);
            return result;
        }
        List<ServerBean> array =  new ArrayList<ServerBean>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList(resultDB.sql,resultDB.args);
        for (Map<String,Object> map : list){
            ServerBean model = selectRow(map);
            array.add(model);
        }
        result.success(array);
        return result;
    }

}
