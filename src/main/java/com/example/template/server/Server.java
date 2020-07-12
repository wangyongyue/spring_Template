package com.example.template.server;

import com.example.template.tool.ResultDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Server {
    @Autowired
    ResultDB resultDB;

    public ResultDB selectSql(){

        resultDB.args = new  Object[]{5};
        resultDB.sql = "select * from word_w where ID = ? ";
        return resultDB;
    }
    public ResultDB insertSql(){ return resultDB; }
    public ResultDB updateSql(){ return resultDB; }
    public ResultDB deleteSql(){ return resultDB; }

    public ServerModel selectRow(Map<String,Object> map){
        ServerModel model = new ServerModel();
        model.name = "sdfsds";
        return model;
    }

}
