package com.example.template.server.test;

import com.example.template.server.Server;
import com.example.template.tool.ResultDB;
import org.springframework.stereotype.Service;

@Service
public class Test extends Server {

    @Override
    public ResultDB selectSql() {
        resultDB.args = new  Object[]{5};
        resultDB.sql = "select * from word_w where ID = ? ";
        return resultDB;
    }
}
