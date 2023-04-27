package com.dasoops.common.conf

import com.dasoops.common.serializer.String2DateTimeConverter
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * 表单字段2DataEnum解析器
 * @author DasoopsNicole@Gmail.com
 * @date  2023/02/27
 * @see [BaseDateTimeSerializerConfiguration]
 */
abstract class BaseDateTimeSerializerConfiguration(vararg pattern: String) : WebMvcConfigurer {
    init {
        String2DateTimeConverter.addAll(pattern)
    }

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(String2DateTimeConverter)
    }
}