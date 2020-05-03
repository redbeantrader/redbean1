package com.management.property.shiro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)//不显示非空字段
public class PageUtil<T> {

    public PageUtil() {
    }

    public PageUtil(List<T> data, Integer count, Integer code, String msg) {
        this.data = data;//数据
        this.count = count;
        this.code = code;//状态
        this.msg = msg;//返回消息
    }

    public PageUtil(Integer codes, String msgs) {
        this.code = codes;
        this.msg = msgs;
    }

    public PageUtil(List<T> data, Integer count) {
        this.data = data;
        this.count = count;
    }

    private PageUtil(Integer codes){
        this.code=codes;
    }

    private PageUtil(Integer codes,T datas){
        this.code=codes;
        this.Ldata=datas;
    }

    private PageUtil(Integer codes,T datas,String msgs){
        this.code=codes;
        this.Ldata=datas;
        this.msg=msgs;
    }

    @JsonIgnore
    public boolean isSucess(){
        return this.code==0;
    }

    public static PageUtil ServerResponseSucess(){
        return new PageUtil(0);
    }

    public static <T> PageUtil ServerResponseSucess(T data){
        return new PageUtil(0,data);
    }

    public static <T> PageUtil ServerResponseSucess(T data,String msg){
        return new PageUtil(0,data,msg);
    }

    public static PageUtil ServerResponseError(Integer codes){
        return new PageUtil(codes);
    }
    public static PageUtil ServerResponseError(Integer codes,String msg){
        return new PageUtil(codes,null,msg);
    }

    private List<T> data;

    private T Ldata;

    private Integer count;

    private Integer code = 0;

    private String msg = "";

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public T getLdata() {
        return Ldata;
    }

    public void setLdata(T ldata) {
        Ldata = ldata;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}