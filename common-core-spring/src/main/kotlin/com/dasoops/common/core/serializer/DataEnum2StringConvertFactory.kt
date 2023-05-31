package com.dasoops.common.core.serializer

import com.dasoops.common.json.core.dataenum.IntDataEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

open class DataEnum2StringConvertFactory : ConverterFactory<IntDataEnum, String> {
    override fun <T : String> getConverter(targetType: Class<T>): Converter<IntDataEnum, T> {
        return IDbColumnEnumToStringConvert()
    }

    @Suppress("UNCHECKED_CAST")
    private class IDbColumnEnumToStringConvert<T> : Converter<IntDataEnum, T> {
        override fun convert(source: IntDataEnum): T {
            return source.data.toString() as T
        }
    }
}