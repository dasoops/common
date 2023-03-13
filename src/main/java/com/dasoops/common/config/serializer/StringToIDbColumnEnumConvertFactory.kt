package com.dasoops.common.config.serializer

import com.dasoops.common.entity.enums.database.IDbColumnEnum
import com.dasoops.common.exception.DataResolverException
import com.dasoops.common.util.dbcolumn.DbColumnUtil
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

/**
 * 字符串 转 数据库枚举 工厂
 * @title: StringToIDbColumnEnumConvertFactor
 * @classPath com.dasoops.common.config.inner.StringToIDbColumnEnumConvertFactor
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [StringToIDbColumnEnumConvertFactory]
 */
open class StringToIDbColumnEnumConvertFactory : ConverterFactory<String, IDbColumnEnum> {
    override fun <T : IDbColumnEnum> getConverter(targetType: Class<T>): Converter<String, T> {
        return StringToIDbColumnEnumConvert(targetType)
    }

    private class StringToIDbColumnEnumConvert<T : IDbColumnEnum>(private val enumType: Class<out T>) : Converter<String, T> {
        override fun convert(source: String): T {
            return DbColumnUtil.getBy(enumType, Integer.valueOf(source)) ?: throw DataResolverException.PARAMETER_RESLOVE_ERROR.get()
        }
    }
}