package com.example.template.controller;

import com.example.template.server.ServerModel;
import com.example.template.server.Server;
import com.example.template.tool.Result;
import com.example.template.tool.ResultDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SelectController {
    @Autowired
    Result result;
    @Autowired
    JdbcTemplate jdbcTemplate;


    public Server server;
    public Result run(){
        ResultDB resultDB = server.selectSql();
        System.out.println(resultDB.sql);
        System.out.println(JSONObject.wrap(resultDB.args));

        if (resultDB.error != null){
            result.fail(resultDB.error);
            return result;
        }
        List<ServerModel> array =  new ArrayList<ServerModel>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList(resultDB.sql,resultDB.args);
        for (Map<String,Object> map : list){
            ServerModel model = server.selectRow(map);
            array.add(model);
        }
        result.success(array);
        return result;
    }
    public void setServer(Server server) { this.server = server; }
    public Server getServer() { return server; }
}
