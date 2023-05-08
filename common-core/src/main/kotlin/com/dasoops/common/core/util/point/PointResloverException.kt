package com.dasoops.common.core.util.point

import com.dasoops.common.core.IExceptionEnum
import com.dasoops.common.core.exception.CustomException

/**
 * 点解析异常枚举(204xx)
 * @title: PointResloverExceptionEnum
 * @classPath com.dasoops.common.core.util.point.PointResloverExceptionEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @version 1.0.0
 * @see [PointResloverException]
 */
enum class PointResloverException(override val message: String) : IExceptionEnum {

    SIZE_NOT_MATCH("集合长度不匹配"),
    REPEAT_INPUT("重复的下标"),
    NULL("不允许为null"),
    UNDEFINED_TYPE("未定义的类型"),
    UNDEFINED_VALUE("未定义的值"),
    ;

    override val code: Int = 20400 + ordinal
    override fun get() = PointResloverExceptionEntity()
    inner class PointResloverExceptionEntity() : CustomException(this)
}