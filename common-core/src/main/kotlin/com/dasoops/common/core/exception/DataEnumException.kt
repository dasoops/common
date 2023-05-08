package com.dasoops.common.exception

import com.dasoops.common.core.IExceptionEnum


/**
 * 数据枚举异常(207xx)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/30
 * @see [DataEnumException]
 */
enum class DataEnumException(override val message: String) : IExceptionEnum {

    NOT_ENUM("不是一个enum类"),
    UNDEFINEND_VALUE("未定义的枚举值"),
    ;

    override val code: Int = 20700 + ordinal
    override fun get() = DataEnumExceptionEntity()

    inner class DataEnumExceptionEntity : CustomException(this)
}