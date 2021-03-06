package com.oxinhagg.webapp;

import com.oxinhagg.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        System.out.println(r);
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        //TODO : invoke r.toString via reflection
        Method method = r.getClass().getDeclaredMethod("toString");
        System.out.println(method.invoke(r));

        System.out.println(r);
    }
}
