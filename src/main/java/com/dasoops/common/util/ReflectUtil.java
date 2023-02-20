package com.dasoops.common.util;

import cn.hutool.core.util.TypeUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title ReflectUtil
 * @classPath com.dasoops.common.util.ReflectUtil
 * @date 2023/02/19
 * @description 反射工具
 * @see ReflectUtil
 */
@Slf4j
public class ReflectUtil extends cn.hutool.core.util.ReflectUtil {
    @SuppressWarnings("unchecked")
    public static <T> T getGenericInstance(Class<?> clazz) {
        ParameterizedType superClass = (ParameterizedType) clazz.getGenericSuperclass();
        return cn.hutool.core.util.ReflectUtil.newInstance((Class<T>) TypeUtil.getTypeArgument(superClass));
    }
}
