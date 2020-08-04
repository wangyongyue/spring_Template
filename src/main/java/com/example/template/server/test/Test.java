package com.example.template.server.test;

import com.example.template.beans.TestBean;
import com.example.template.server.Server;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Test extends Server {

    public List<TestBean> array = new ArrayList<>();

    @Override
    public Boolean loadSql() {
        args = new Object[]{5};
        sql = "select * from word_w where ID = ? ";
        return true;
    }
    @Override
    public Boolean handleData(List<Map<String, Object>> list) {
        this.list = new ArrayList<>();
        for (Map<String,Object> map : list){
            TestBean object = new TestBean();
            object.name = "test";
            this.list.add(object);
        }
        return true;
    }

}
