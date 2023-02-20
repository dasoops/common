package com.dasoops.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.dasoops.common.entity.enums.ExceptionEnum;
import com.dasoops.common.exception.LogicException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @title Convert
 * @classPath com.dasoops.common.util.Convert
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/28
 * @version 1.0.0
 * @description 转换工具类
 */
public class Convert {

    /**
     * 简单转stringMap,方便redis使用
     *
     * @param toConvertMap 需要转换的map
     * @return {@link Map}<{@link String}, {@link String}>
     */
    public static Map<String, String> toStrMap(Map<?, ?> toConvertMap) {
        return toConvertMap.entrySet().stream().collect(Collectors.toMap(
                entry -> String.valueOf(entry.getKey()),
                entry -> String.valueOf(entry.getValue())
        ));
    }

    /**
     * 简单类型转换
     *
     * @param clazz clazz
     * @param t     t
     * @return {@link R}
     */
    public static <R, T> R to(T t, Class<R> clazz) {
        R r;
        try {
            r = ReflectUtil.newInstance(clazz);
            BeanUtil.copyProperties(t, r);
        } catch (Exception e) {
            throw new LogicException(ExceptionEnum.TYPE_CONVERT);
        }
        return r;
    }

    /**
     * 简单类型转换
     *
     * @param clazz clazz
     * @param tList obj集合
     * @return {@link List}<{@link E}>
     */
    public static <E, T> List<E> to(List<T> tList, Class<E> clazz) {
        return tList.stream().map(t -> {
            try {
                E e = ReflectUtil.newInstance(clazz);
                BeanUtil.copyProperties(t, e);
                return e;
            } catch (Exception e) {
                throw new LogicException(ExceptionEnum.TYPE_CONVERT, e);
            }
        }).collect(Collectors.toList());
    }
}
