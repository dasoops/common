package com.dasoops.common.serializer

import com.dasoops.common.entity.dataenum.DataEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

open class DataEnum2StringConvertFactory : ConverterFactory<DataEnum, String> {
    override fun <T : String> getConverter(targetType: Class<T>): Converter<DataEnum, T> {
        return IDbColumnEnumToStringConvert()
    }

    private class IDbColumnEnumToStringConvert<T> : Converter<DataEnum, T> {
        override fun convert(source: DataEnum): T {
            return source.data.toString() as T
        }
    }
}