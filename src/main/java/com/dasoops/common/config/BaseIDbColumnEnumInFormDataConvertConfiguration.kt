package com.dasoops.common.config

import com.dasoops.common.config.serializer.IDbColumnEnumToIntConvertFactory
import com.dasoops.common.config.serializer.IntegerToIDbColumnEnumConvertFactory
import com.dasoops.common.config.serializer.StringToIDbColumnEnumConvertFactory
import org.springframework.core.convert.converter.ConverterFactory
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * 数据库字段通过非json格式提交转换配置类
 * @title: BaseIDbColumnEnumInFormDataConvertConfiguration
 * @classPath com.dasoops.common.config.BaseIDbColumnEnumInFormDataConvertConfiguration
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [BaseIDbColumnEnumInFormDataConvertConfiguration]
 */
open class BaseIDbColumnEnumInFormDataConvertConfiguration : WebMvcConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        // Int -> DbColumnEnum 解析器
        registry.addConverterFactory(IntegerToIDbColumnEnumConvertFactory())
        // String -> DbColumnEnum 解析器
        registry.addConverterFactory(StringToIDbColumnEnumConvertFactory())
        // DbColumnEnum -> string解析器
        registry.addConverterFactory(IDbColumnEnumToIntConvertFactory())
        // DbColumnEnum -> string解析器
        registry.addConverterFactory(IDbColumnEnumToStringConvertFactory())
    }
}