package com.dasoops.common.json

import com.dasoops.common.json.core.toJsonStr
import org.springframework.core.convert.converter.Converter

/**
 * 默认对象转换器(json)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/11
 */
class DefaultJsonConvert<Key : Any> : Converter<Key, String> {
    override fun convert(source: Key): String = source.toJsonStr()
}
