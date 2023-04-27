package com.dasoops.common.serializer

import cn.hutool.core.date.DatePattern
import cn.hutool.core.date.DateUtil
import com.dasoops.common.ConversionService
import com.fasterxml.jackson.databind.util.StdConverter
import org.springframework.core.convert.converter.Converter
import java.util.*

/**
 * string2Date 转换器
 * @author DasoopsNicole@Gmail.com
 * @date 2023-04-27
 */
object String2DateTimeConverter : Converter<String, Date>, LinkedHashSet<String>() {

    init {
        super.addAll(
            listOf(
                DatePattern.NORM_DATETIME_PATTERN,
                DatePattern.NORM_DATETIME_MINUTE_PATTERN,
                DatePattern.UTC_SIMPLE_PATTERN,
                "yyyy-MM-dd'T'HH:mm",
            )
        )
        ConversionService.addConverter(this)
    }

    override fun convert(source: String): Date {
        return DateUtil.parse(source, *toTypedArray())
    }

    object Jackson : StdConverter<String, Date>() {
        override fun convert(source: String) = String2DateTimeConverter.convert(source)
    }
}
