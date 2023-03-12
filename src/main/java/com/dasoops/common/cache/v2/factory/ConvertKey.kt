package com.dasoops.common.cache.v2.factory

import org.springframework.core.convert.converter.Converter

/**
 * key转换接口
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/12
 * @see [ConvertKey]
 */
interface ConvertKey<Key : Any> {
    val keyConvert: Converter<Key, String>

    fun convert(key: Key?): String {
        return key?.run { keyConvert.convert(this) } ?: ""
    }
}
