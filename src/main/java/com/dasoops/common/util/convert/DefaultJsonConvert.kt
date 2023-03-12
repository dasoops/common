package com.dasoops.common.util.convert

import com.dasoops.common.util.json.toJsonStr
import org.springframework.core.convert.converter.Converter

/**
 * 默认对象转换器(json)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/11
 */
class DefaultJsonConvert<Key> : Converter<Key, String> {
    override fun convert(source: Key): String = source?.toJsonStr() ?: "null"
}
