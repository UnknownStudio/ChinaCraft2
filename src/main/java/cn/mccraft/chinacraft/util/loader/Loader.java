package cn.mccraft.chinacraft.util.loader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * A helper that can load lots of annotated fields into Minecraft.
 * You should implement {@link #loadForAnnotation(Annotation, Field)} yourself if you want to use this system.
 * And invoke {@link #loadAllFieldsInClass(Class, Class)} in the function
 * annotated with {@link cn.mccraft.chinacraft.util.loader.annotation.Load} annotation.
 *
 * 一个可以方便快捷的帮助类，可以实现通过注解自动注册物品等。
 * 如果你想使用该特性，你需要实现{@link #loadForAnnotation(Annotation, Field)}函数。
 * 并在{@link cn.mccraft.chinacraft.util.loader.annotation.Load}注解注释的函数中调用
 * {@link #loadAllFieldsInClass(Class, Class)}函数。
 * @param <T> Type of Annotation
 */
public interface Loader {
    /**
     * Load all fields in a class.
     * @param annotationClass annotation to be checked
     * @param clz class to be loaded
     */
    /*default void loadAllFieldsInClass(Class<T> annotationClass, Class<?> clz) {
        for (Field field : clz.getFields()) {
            field.setAccessible(true);
            if (field.getAnnotation(annotationClass)!=null)
                loadForAnnotation(field.getAnnotation(annotationClass), field);
        }
    }*/

    /**
     * Load a field for a annotation.
     * @param annotation annotation instance
     * @param field field to be loaded
     */
    //default void loadForAnnotation(T annotation, Field field) {}
}
