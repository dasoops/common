package com.dasoops.common.serializer

import com.dasoops.common.entity.dataenum.DataEnum
import com.dasoops.common.exception.DataResolverException
import com.dasoops.common.util.DataEnumUtil
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

/**
 * 整形 转 数据库枚举 工厂
 * @title: IntegerToIDbColumnEnumConvertFactory
 * @classPath com.dasoops.common.config.inner.IntegerToIDbColumnEnumConvertFactory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [Integer2DataEnumConvertFactory]
 */
open class Integer2DataEnumConvertFactory : ConverterFactory<Int, DataEnum> {
    override fun <T : DataEnum> getConverter(targetType: Class<T>): Converter<Int, T> {
        return IntegerToIDbColumnEnumConvert(targetType)
    }

    private class IntegerToIDbColumnEnumConvert<T : DataEnum>(private val enumType: Class<out T>) : Converter<Int, T> {
        override fun convert(source: Int): T {
            return DataEnumUtil.getBy(enumType, source) ?: throw DataResolverException.PARAMETER_RESLOVE_ERROR.get()
        }
    }
}