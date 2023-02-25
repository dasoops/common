package com.dasoops.common.util.json

import com.dasoops.common.entity.enums.exception.IExceptionEnum
import com.dasoops.common.exception.CustomException
import com.dasoops.common.util.IUtilExceptionEnum
import com.dasoops.common.util.UtilException

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title ExportExceptionEnum
 * @classPath com.dasoops.common.util.export.ExportExceptionEnum
 * @date 2022/12/30
 * @description json异常枚举(903xx)
 * @see IExceptionEnum
 */
enum class JsonExceptionEnum(private val msg: String) : IUtilExceptionEnum {
    /**
     * eem快速生成
     */
    CANT_CAST("不允许的转换");

    override fun getCode(): Int {
        return 90300 + ordinal
    }

    override fun getMsg(): String {
        return msg
    }

    override fun getException(): CustomException {
        return JsonException()
    }

    inner class JsonException : UtilException(this)
}