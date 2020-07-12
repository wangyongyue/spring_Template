package com.example.template.tool;

import org.springframework.stereotype.Service;


@Service
public class ResultDB {
    public String sql;
    public String error;
    public Object[] args;

    public void setSql(String sql) { this.sql = sql; }
    public String getSql() { return sql; }
    public void setError(String error) { this.error = error; }
    public String getError() { return error; }
    public void setArgs(Object[] args) { this.args = args; }
    public Object[] getArgs() { return args; }
}