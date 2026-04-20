package com.solvd.university.utils;

import com.solvd.university.annotations.Display;
import com.solvd.university.annotations.Sensitive;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static void printObject(Object obj) {
        Class<?> clazz = obj.getClass();

        System.out.println("Class: " + clazz.getSimpleName());

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value = field.get(obj);

                // Mask sensitive fields
                if (field.isAnnotationPresent(Sensitive.class)) {
                    value = "***MASKED***";
                }

                // Custom display name
                String name = field.getName();
                if (field.isAnnotationPresent(Display.class)) {
                    Display display = field.getAnnotation(Display.class);
                    if (!display.value().isEmpty()) {
                        name = display.value();
                    }
                }

                System.out.println(name + ": " + value);

            } catch (IllegalAccessException e) {
                System.out.println("Error accessing field: " + field.getName());
            }
        }
    }
}