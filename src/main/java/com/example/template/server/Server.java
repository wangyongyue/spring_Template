package com.example.template.server;

import com.example.template.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class Server {

    @Autowired
    public Result result;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String error;
    public String sql;
    public Object[] args;
    public List list;

    public Boolean loadSql(){
        args = new  Object[]{5};
        sql = "select * from word_w where ID = ? ";
        return true;
    }
    public Boolean handleData(List<Map<String,Object>> list){
        return true;
    }
    public Boolean handleOtherData(int re){
        if (re == 0){
            error = "执行失败";
            return false;
        }
        return true;
    }
    public Boolean run(){
        return select();
    }

    private Boolean select(){
        if (loadSql() == false){ return false;}
        System.out.println(sql);
        System.out.println(JSONObject.wrap(args));
        if (sql.startsWith("select") == false){
            int re = jdbcTemplate.update(sql);
            return handleOtherData(re);
        }
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql,args);
        return handleData(list);
    }

}
