package com.dasoops.common.cache

import org.springframework.core.convert.converter.Converter

/**
 * 缓存key生成器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 */
object CacheKey {

    /**
     * 构建缓存key值
     */
    fun <T : Any> `for`(keyConvert: Converter<T, String>, keyStr: String, innerKey: String?, key: T): String {
        return "$keyStr:${if (innerKey == null) "" else "$innerKey:"}${keyConvert.convert(key)}"
    }

}