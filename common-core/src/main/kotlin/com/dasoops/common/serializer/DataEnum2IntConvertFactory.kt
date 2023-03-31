package com.dasoops.common.serializer

import com.dasoops.common.entity.dataenum.DataEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

/**
 * 数据枚举toInt转换工厂
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [DataEnum2IntConvertFactory]
 */
open class DataEnum2IntConvertFactory : ConverterFactory<DataEnum, Int> {
    override fun <T : Int> getConverter(targetType: Class<T>): Converter<DataEnum, T> {
        return IDbColumnEnumToIntConvert()
    }

    private class IDbColumnEnumToIntConvert<T> : Converter<DataEnum, T> {
        override fun convert(source: DataEnum): T {
            return source.data as T
        }
    }
}