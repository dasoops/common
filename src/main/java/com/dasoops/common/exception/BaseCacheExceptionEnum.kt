package com.dasoops.common.exception

import com.dasoops.common.entity.enums.exception.IExceptionEnum

enum class BaseCacheExceptionEnum(private val msg: String) : IExceptionEnum {

    UNDEFINED_CAST("未实现的转换"),
    ;

    override fun getCode(): Int {
        return 90200 + ordinal;
    }

    override fun getMsg(): String {
        return msg;
    }

    override fun getException(): BaseCacheException {
        return BaseCacheException()
    }

    inner class BaseCacheException : CustomException(this)
}