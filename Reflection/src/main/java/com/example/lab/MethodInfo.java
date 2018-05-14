package com.example.lab;


import java.lang.reflect.Method;

public class MethodInfo {

    private Method method;
    private String name;

    public MethodInfo(Method method, String name) {
        this.method = method;
        this.name = name;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String toString(){
        return name;
    }
}
