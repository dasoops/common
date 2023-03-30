package com.dasoops.common.util

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.util.ReflectUtil
import org.springframework.beans.BeanUtils
import java.util.stream.Collectors

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title Convert
 * @classPath com.dasoops.common.util.Convert
 * @date 2022/12/28
 * @description 转换工具类
 */
object Converter {
    /**
     * 简单转stringMap,方便redis使用
     *
     * @param toConvertMap 需要转换的map
     * @return [Map]<[String], [String]>
     */
    fun toStrMap(toConvertMap: Map<*, *>): Map<String, String> {
        return toConvertMap.entries.stream().collect(Collectors.toMap(
            { it.key.toString() },
            { it.value.toString() }
        ))
    }

    /**
     * 简单类型转换
     *
     * @param clazz clazz
     * @param t t
     * @return [R]
     */
    fun <R : Any, T : Any> to(t: T, clazz: Class<R>): R {
        val r = ReflectUtil.newInstance(clazz)!!
        BeanUtils.copyProperties(t, r)
        return r
    }

    /**
     * 简单类型转换
     *
     * @param clazz clazz
     * @param tList obj集合
     * @return [List]<[E]>
     */
    fun <E, T> to(tList: List<T>, clazz: Class<E>): List<E> {
        return tList.stream().map {
            val e = ReflectUtil.newInstance(clazz)
            BeanUtil.copyProperties(it, e)
            e
        }.collect(Collectors.toList())
    }
}