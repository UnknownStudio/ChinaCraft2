package cn.mccraft.chinacraft.util.loader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface ILoader<T extends Annotation> {
    default void loadAllFieldsInClass(Class<T> annotationClass, Class<?> clz) {
        for (Field field : clz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getAnnotation(annotationClass) != null)
                loadForAnnotation(field.getAnnotation(annotationClass), field);
        }
    }

    default void loadForAnnotation(T annotation, Field field) {}
}
