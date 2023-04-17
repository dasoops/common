package com.dasoops.common

import org.springframework.core.convert.converter.Converter


/**
 * 默认对象转换器(toString)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/11
 */
class DefaultToStringConvert<Key : Any> : Converter<Key, String> {
    override fun convert(source: Key): String = source.toString()
}
