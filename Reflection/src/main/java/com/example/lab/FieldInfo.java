package com.example.lab;

import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.Field;

public class FieldInfo {

    private Field field;
    private SimpleStringProperty name;
    private SimpleStringProperty type;
    private SimpleStringProperty value;

    public FieldInfo(Field field, String name, String type, String value) {
        this.field = field;
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.value = new SimpleStringProperty(value);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }


}
