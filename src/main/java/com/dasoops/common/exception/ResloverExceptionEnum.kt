package com.dasoops.common.exception

import com.dasoops.common.entity.enums.exception.IExceptionEnum

/**
 * @title: ResloverExceptionEnum
 * @classPath com.dasoops.common.exception.ResloverExceptionEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/19
 * @version 1.0.0
 * @description: 解析器异常枚举
 * @see [Enum]
 */
enum class ResloverExceptionEnum(private val msg: String) : IExceptionEnum {

    PARAMETER_RESLOVE_ERROR("输入参数解析异常"),
    MISSING_REQUIRED_PARAM("缺少必填参数"),
    OUT_OF_BOUNDS("参数超出范围"),
    ;

    override fun getCode(): Int {
        return 1000 + ordinal;
    }

    override fun getMsg(): String {
        return msg
    }

    override fun getException(): CustomException {
        return ResloverException()
    }

    inner class ResloverException : CustomException(this)
}