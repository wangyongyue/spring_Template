package com.example.template.router;

import com.example.template.controller.SelectController;
import com.example.template.server.Server;
import com.example.template.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.POST)
public class Router {
    @Autowired
    SelectController selectController;

    @Autowired
    Server server;
    @RequestMapping(value = "/abc")
    public Result abc(){

        selectController.server = server;
        Result result = selectController.run();
        return result;
        
    }

}
