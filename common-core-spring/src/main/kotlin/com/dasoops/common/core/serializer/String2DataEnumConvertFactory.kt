package com.dasoops.common.core.serializer

import com.dasoops.common.core.entity.dataenum.DataEnum
import com.dasoops.common.exception.DataResolverException
import com.dasoops.common.core.util.DataEnumUtil
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

/**
 * 字符串 转 数据枚举 工厂
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [String2DataEnumConvertFactory]
 */
open class String2DataEnumConvertFactory : ConverterFactory<String, DataEnum> {
    override fun <T : DataEnum> getConverter(targetType: Class<T>): Converter<String, T> {
        return StringToIDbColumnEnumConvert(targetType)
    }

    private class StringToIDbColumnEnumConvert<T : DataEnum>(private val enumType: Class<out T>) : Converter<String, T> {
        override fun convert(source: String): T {
            return DataEnumUtil.getBy(enumType, Integer.valueOf(source)) ?: throw DataResolverException.PARAMETER_RESLOVE_ERROR.get()
        }
    }
}