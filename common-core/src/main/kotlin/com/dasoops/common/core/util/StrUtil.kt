package com.dasoops.common.core.util

import cn.hutool.core.util.ReflectUtil
import cn.hutool.core.util.StrUtil
import java.util.*
import java.util.stream.Collectors

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title FormatUtil
 * @classPath com.dasoops.common.core.util.FormatUtil
 * @date 2023/02/16
 * @description 格式化文本工具
 * @see [StrUtil]
 */
object StrUtil : StrUtil() {
    /**
     * 格式化文本forObj
     *
     * @param templateStr 模板str
     * @param obj obj
     * @return [String]
     */
    fun formatForObj(templateStr: String?, obj: Any): String {
        val fieldArray = ReflectUtil.getFields(obj.javaClass)
        val paramMap = Arrays.stream(fieldArray)
            .collect(Collectors.toMap(
                { it.name },
                {
                    val fieldValue = ReflectUtil.getFieldValue(obj, it)
                    Objects.requireNonNullElse(fieldValue, "")
                }
            ))
        return format(templateStr, paramMap)
    }

    /**
     * 首字母大写(进行字母的ascii编码前移，效率是最高的)
     *
     * @param fieldName 需要转化的字符串
     */
    fun getMethodName(fieldName: String): String {
        val chars = fieldName.toCharArray()
        chars[0] = toUpperCase(chars[0])
        return String(chars)
    }

    /**
     * 字符转成大写
     *
     * @param c 需要转化的字符
     */
    fun toUpperCase(c: Char): Char {
        var c = c
        if (c.code in 97..122) {
            c = (c.code xor 32).toChar()
        }
        return c
    }
}