package com.example.lab;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassLoader {

    public static Class<?> loadedClass;
    public static Object sampleObject;

    public static Field[] fields;
    public static Method[] methods;

    private boolean loadedSuccesfully = false;

    // utworzenie obiektu danej klasy
    public ClassLoader(String className){
        try {
            loadedClass = Class.forName(className); // wczytaj klase z podanego stringa
            sampleObject = loadedClass.newInstance(); // utworz obiekt tej klasy
            fields = loadedClass.getDeclaredFields(); // pobierz pola klasy
            methods = loadedClass.getDeclaredMethods(); // pobierz metody klasy
            loadedSuccesfully = true; // operacja przebiegla pomyslnie
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot load object");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    // czy pomyślnie pobrano obiekt
    public boolean isOk(){
        return loadedSuccesfully;
    }
}
