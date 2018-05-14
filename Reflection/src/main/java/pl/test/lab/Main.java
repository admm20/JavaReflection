package pl.test.lab;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class<?> sampleClass = Class.forName("pl.test.lab.Rectangle");

        Object sampleObject = sampleClass.newInstance();

        Field[] fields = sampleClass.getDeclaredFields();
        Method[] methods = sampleClass.getDeclaredMethods();
/*
        for (Method m : sampleClass.getDeclaredMethods()){
            System.out.println("Metoda: " + m.toString());
        }
        */

        for (Field f : fields){
            f.setAccessible(true);
            //f.set(sampleObject, 15.3);
            System.out.println("Pole: " + f.toString() + " TYP: " + f.getType());
            System.out.println("WARTOSC: " + f.get(sampleObject).toString());
            if(f.getType().isEnum()){
                f.setAccessible(true);
                //f.set(sampleObject, 50);
                //System.out.println("WARTOSC: " + f.getInt(sampleObject));
                /*
                System.out.println("WARTOSC PRZED: " + f.get(sampleObject).toString());
                for(Object o : f.getType().getEnumConstants()){
                    System.out.println(o.toString());
                    f.set(sampleObject, o);
                }
                System.out.println("WARTOSC PO: " + f.get(sampleObject).toString());
                */
            }
        }/*
        for(Method m : methods){
            System.out.println("Metoda: " + m.getName() + " ZWRACA: " + m.getReturnType() + " ACCESS: " + m.toString());
        }*/

    }
}
