package com.dasoops.common.util;

import cn.hutool.core.util.ReflectUtil;
import lombok.val;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title FormatUtil
 * @classPath com.dasoops.common.util.FormatUtil
 * @date 2023/02/16
 * @description 格式化文本工具
 * @see [StrUtil]
 */

public class StrUtil extends cn.hutool.core.util.StrUtil {
    /**
     * 格式化文本forObj
     *
     * @param templateStr 模板str
     * @param obj obj
     * @return {@link String}
     */
    public static String formatForObj(String templateStr, Object obj) {
        var fieldArray = ReflectUtil.getFields(obj.getClass());
        var paramMap = Arrays.stream(fieldArray)
                .collect(Collectors.toMap(
                        Field::getName,
                        it -> {
                            Object fieldValue = ReflectUtil.getFieldValue(obj, it);
                            if (fieldValue == null) {
                                return "";
                            }
                            return fieldValue;
                        }
                ));
        return format(templateStr, paramMap);
    }

    /**
     * 首字母大写(进行字母的ascii编码前移，效率是最高的)
     *
     * @param fieldName 需要转化的字符串
     */
    public static String getMethodName(String fieldName) throws Exception {
        char[] chars = fieldName.toCharArray();
        chars[0] = StrUtil.toUpperCase(chars[0]);
        return String.valueOf(chars);
    }


    /**
     * 字符转成大写
     *
     * @param c 需要转化的字符
     */
    public static char toUpperCase(char c) {
        if (97 <= c && c <= 122) {
            c ^= 32;
        }
        return c;
    }

}
