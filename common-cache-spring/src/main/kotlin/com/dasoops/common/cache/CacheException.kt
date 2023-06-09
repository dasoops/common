package com.dasoops.common.cache

import com.dasoops.common.core.IExceptionEnum
import com.dasoops.common.core.exception.CustomException

/**
 * 缓存异常(205xx)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [CacheException]
 */
enum class CacheException(override val message: String) : IExceptionEnum {
    ;
    inner class CacheExceptionEntity : CustomException(this)

    override val code: Int = 20500 + ordinal
    override fun get() = CacheExceptionEntity()
}