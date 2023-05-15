package com.example.javaweb.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtils {
    @SuppressWarnings("unchecked")
    public static  <T> T toModel(@NotNull Class<T> clazz, @NotNull HttpServletRequest request) {
        T object = null;
        try {
            object = clazz.newInstance();
            BeanUtils.populate(object,request.getParameterMap());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException  e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}
