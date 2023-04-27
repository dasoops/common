package com.dasoops.common.conf

import com.dasoops.common.serializer.DataEnum2IntConvertFactory
import com.dasoops.common.serializer.DataEnum2StringConvertFactory
import com.dasoops.common.serializer.Integer2DataEnumConvertFactory
import com.dasoops.common.serializer.String2DataEnumConvertFactory
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

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