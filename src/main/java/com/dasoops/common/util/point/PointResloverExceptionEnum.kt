package com.dasoops.common.util.point

import com.dasoops.common.entity.enums.exception.IExceptionEnum
import com.dasoops.common.exception.CustomException

/**
 * 点解析异常枚举(101xx)
 * @title: PointResloverExceptionEnum
 * @classPath com.dasoops.common.util.point.PointResloverExceptionEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @version 1.0.0
 * @see [PointResloverExceptionEnum]
 */
enum class PointResloverExceptionEnum(private val msg: String) : IExceptionEnum {

    SIZE_NOT_MATCH("集合长度不匹配"),
    REPEAT_INPUT("重复的下标"),
    NULL("不允许为null"),
    UNDEFINED_TYPE("未定义的类型"),
    UNDEFINED_VALUE("未定义的值"),
    ;

    override fun getCode(): Int {
        return 10100 + ordinal
    }

    override fun getMsg(): String {
        return msg
    }

    override fun getException(): PointResloverException {
        return PointResloverException()
    }

    inner class PointResloverException : CustomException(this)
}