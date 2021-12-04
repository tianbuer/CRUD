package com.tdd.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianbuer
 * @date 2021/11/30
 */
public class Mag {
    private Integer code;
    private String mag;
    private  Map<String,Object> extend;


    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public static  Mag success(){
        Mag result = new Mag();
        result.setCode(100);
        result.setMag("处理成功");
        return result;
    }
    public static  Mag fail(){
        Mag result = new Mag();
        result.setCode(200);
        result.setMag("处理失败");
        return result;
    }
    public Mag add(String key,Object value){
        extend=new HashMap<>();
        extend.put(key, value);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

}
