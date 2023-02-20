package com.dasoops.common.util;

import com.dasoops.common.entity.enums.ExceptionEnum;
import com.dasoops.common.exception.LogicException;
import com.dasoops.common.util.entity.ReflectUtilExceptionEnum;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * 反射工具包
 *
 * @author rongdi
 * @date 2021-03-06
 * @blog https://www.cnblogs.com/rongdi
 */
@Slf4j
public class ReflectUtil {

    private static final String SET_METHOD_PREFIX = "set";

    public static <T> T getGenericInstance(Class<?> clazz, Integer index) {
        ParameterizedType superClass = (ParameterizedType) clazz.getGenericSuperclass();
        @SuppressWarnings("all")
        Class<T> type = (Class<T>) superClass.getActualTypeArguments()[index];
        try {
            return type.getConstructor().newInstance();
        } catch (Exception e) {
            throw new LogicException(ExceptionEnum.NO_NULL_PARAMETER_CONSTRUCTOR);
        }
    }

    /**
     * 往父类直到查询到指定方法
     *
     * @param object
     * @param methodName
     * @return
     */
    public static Method getMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                log.error("", e);
            }
        }
        return null;
    }

    /**
     * 往父类直到查询到指定属性
     *
     * @param object
     * @param fieldName
     * @return
     */
    public static Field getField(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                log.error("", e);
            }
        }
        return null;
    }

    /**
     * 实例化对象
     * 弃用,写的太痛苦了,不如找个工具类调用,考虑的比我多多了,香死了
     *
     * @param clazz clazz
     * @return {@link T}
     * @throws LogicException 逻辑异常
     */
    @Deprecated
    public static <T> T newInstance(Class<T> clazz) throws LogicException {
        try {
            return clazz.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new LogicException(ReflectUtilExceptionEnum.NO_SUCH_CONSTRUCTOR);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new LogicException(ReflectUtilExceptionEnum.INSTANCE_ERROR);
        }
    }

//    public static void setField(Object obj, Field field, Object param) {
//        Method setMethod = getSetMethod(obj, field);
//        Class<?> paramClass = setMethod.getParameterTypes()[0];
//        if (obj.getClass().isAssignableFrom(paramClass)) {
//
//        }
//        try {
//            if (String.class.isAssignableFrom(paramClass)) {
//                setMethod.invoke(obj, param);
//            } else if (Integer.TYPE.isAssignableFrom(paramClass)) {
//                setMethod.invoke(obj, Integer.valueOf(param));
//            } else if (Long.TYPE.isAssignableFrom(paramClass)) {
//                setMethod.invoke(obj, Long.valueOf(param));
//            } else if (Double.TYPE.isAssignableFrom(paramClass)) {
//                setMethod.invoke(obj, Double.valueOf(param));
//            } else if (Boolean.TYPE.isAssignableFrom(paramClass)) {
//                setMethod.invoke(obj, Boolean.valueOf(param));
//            } else {
//                throw new LogicException(ReflectUtilExceptionEnum.CANT_RESLOVE_PARAM);
//            }
//        } catch (IllegalAccessException e) {
//            throw new LogicException(ReflectUtilExceptionEnum.CANT_INVOKE_METHOD);
//        } catch (InvocationTargetException e) {
//            throw new LogicException(ReflectUtilExceptionEnum.PARAMETER_RESLOVE_CONVERT_ERROR);
//        }
//    }
//
//    private static Method getSetMethod(Object obj, Field field) {
//        Method setMethod;
//        try {
//            setMethod = obj.getClass().getMethod(StrUtil.upperFirstAndAddPre(field.getName(), SET_METHOD_PREFIX), Object.class);
//        } catch (NoSuchMethodException e) {
//            throw new LogicException(ReflectUtilExceptionEnum.NO_SUCH_SET_METHOD);
//        }
//        return setMethod;
//    }
}
