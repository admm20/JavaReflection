package com.example.lab;

public class EnumSelection {

    private Object enumObj;
    private String name;

    EnumSelection(Object enumObj, String name){
        this.enumObj = enumObj;
        this.name = name;
    }

    public Object getEnumObj() {
        return enumObj;
    }

    public String toString(){
        return name;
    }
}
