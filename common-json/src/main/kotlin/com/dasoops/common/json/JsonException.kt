package com.dasoops.common.json

import com.dasoops.common.IException
import com.dasoops.common.exception.CustomException

/**
 * json异常(203xx)
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/30
 * @see [JsonException]
 */
enum class JsonException(override val message: String) : IException {
    /**
     * eem快速生成
     */
    CANT_CAST("不允许的转换");

    override val code: Int = 20300 + ordinal
    override fun get() = JsonExceptionEntity()

    inner class JsonExceptionEntity : CustomException(this)
}