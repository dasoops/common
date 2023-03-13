package com.dasoops.common.exception

import com.dasoops.common.entity.enums.exception.IExceptionEnum

enum class CacheException(private val msg: String) : IExceptionEnum {

    UNDEFINED_CAST("未实现的转换"),
    GET_LOCK_ERROR("获取锁失败"),
    ;

    override fun getCode(): Int {
        return 90200 + ordinal;
    }

    override fun getMsg(): String {
        return msg;
    }

    override fun get(): CacheExceptionEntity {
        return CacheExceptionEntity()
    }

    inner class CacheExceptionEntity : CustomException(this)
}