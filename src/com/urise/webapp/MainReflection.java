package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);  // возможность получать прайват поля и изменять его
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuiid");
        //todo метод инвок объект  invoke r.toString через рефлексию вызвать toString и вызвать антацию. class.getAnnotation
        System.out.println(resume);
    }
}
