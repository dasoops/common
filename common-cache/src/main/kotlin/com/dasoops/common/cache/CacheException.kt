package com.dasoops.common.cache

import com.dasoops.common.IException
import com.dasoops.common.exception.CustomException

/**
 * 缓存异常(205xx)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [CacheException]
 */
enum class CacheException(override val message: String) : IException {
    ;
    inner class CacheExceptionEntity : CustomException(this)

    override val code: Int = 20500 + ordinal
    override fun get() = CacheExceptionEntity()
}