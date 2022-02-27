package com.firefly.entity;

/**
 * @Author fly
 * @Date 2019/6/17
 * @Description
 */
public class SimpleType {
    private Object ret;

    public SimpleType(){

    }

    public SimpleType(Object ret) {
        this.ret = ret;
    }

    public Object getRet() {
        return ret;
    }

    public void setRet(Object ret) {
        this.ret = ret;
    }

    public static SimpleType convert(Object val){
        return new SimpleType(val);
    }
}
