package com.dasoops.common.config

import com.dasoops.common.exception.DataResolverExceptionEnum
import com.dasoops.common.util.dbcolumn.DbColumnUtil
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import com.dasoops.common.entity.enums.database.IDbColumnEnum

/**
 * @title: BaseIDbColumnEnumInFormDataConvertConfiguration
 * @classPath com.dasoops.common.config.BaseIDbColumnEnumInFormDataConvertConfiguration
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/19
 * @version 1.0.0
 * @description: 数据库字段通过非json格式提交转换配置类
 * @see [BaseIDbColumnEnumInFormDataConvertConfiguration]
 */
open class BaseIDbColumnEnumInFormDataConvertConfiguration : WebMvcConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        // int解析器
        registry.addConverterFactory(IntegerToIDbColumnEnumConvertFactor())
        // string解析器
        registry.addConverterFactory(StringToIDbColumnEnumConvertFactor())
    }

    private class IntegerToIDbColumnEnumConvertFactor : ConverterFactory<Int, IDbColumnEnum> {
        override fun <T : IDbColumnEnum> getConverter(targetType: Class<T>): Converter<Int, T> {
            return IntegerToIDbColumnEnumConvert(targetType)
        }

        private class IntegerToIDbColumnEnumConvert<T : IDbColumnEnum>(private val enumType: Class<out T>) : Converter<Int, T> {
            override fun convert(source: Int): T {
                return DbColumnUtil.getBy(enumType, source) ?: throw DataResolverExceptionEnum.PARAMETER_RESLOVE_ERROR.exception
            }
        }
    }

    private class StringToIDbColumnEnumConvertFactor : ConverterFactory<String, IDbColumnEnum> {
        override fun <T : IDbColumnEnum> getConverter(targetType: Class<T>): Converter<String, T> {
            return StringToIDbColumnEnumConvert(targetType)
        }

        private class StringToIDbColumnEnumConvert<T : IDbColumnEnum>(private val enumType: Class<out T>) : Converter<String, T> {
            override fun convert(source: String): T {
                return DbColumnUtil.getBy(enumType, Integer.valueOf(source)) ?: throw DataResolverExceptionEnum.PARAMETER_RESLOVE_ERROR.exception
            }
        }
    }
}