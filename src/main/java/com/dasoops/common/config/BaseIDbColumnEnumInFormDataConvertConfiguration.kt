package com.dasoops.common.config

import com.dasoops.common.config.inner.IntegerToIDbColumnEnumConvertFactory
import com.dasoops.common.config.inner.StringToIDbColumnEnumConvertFactory
import com.dasoops.common.exception.DataResolverExceptionEnum
import com.dasoops.common.util.dbcolumn.DbColumnUtil
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import com.dasoops.common.entity.enums.database.IDbColumnEnum

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
        // int解析器
        registry.addConverterFactory(IntegerToIDbColumnEnumConvertFactory())
        // string解析器
        registry.addConverterFactory(StringToIDbColumnEnumConvertFactory())
    }
}