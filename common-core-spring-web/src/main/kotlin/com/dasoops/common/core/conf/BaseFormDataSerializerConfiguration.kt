package com.dasoops.common.core.conf

import com.dasoops.common.core.serializer.DataEnum2IntConvertFactory
import com.dasoops.common.core.serializer.DataEnum2StringConvertFactory
import com.dasoops.common.core.serializer.Integer2DataEnumConvertFactory
import com.dasoops.common.core.serializer.String2DataEnumConvertFactory
import com.dasoops.common.json.core.dataenum.BooleanEnum
import com.dasoops.common.json.core.dataenum.DataEnum
import com.dasoops.common.json.core.dataenum.IntDataEnum
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.Serializable

/**
 * 表单字段2DataEnum解析器
 * @author DasoopsNicole@Gmail.com
 * @date  2023/02/27
 * @see [BaseFormDataSerializerConfiguration]
 */
abstract class BaseFormDataSerializerConfiguration : WebMvcConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        with(registry) {
            // Int -> DbColumnEnum 解析器
            addConverterFactory(Integer2DataEnumConvertFactory())
            // String -> DbColumnEnum 解析器
            addConverterFactory(String2DataEnumConvertFactory())
            // DbColumnEnum -> Int 解析器
            addConverterFactory(DataEnum2IntConvertFactory())
            // DbColumnEnum -> string解析器
            addConverterFactory(DataEnum2StringConvertFactory())
        }
    }
}