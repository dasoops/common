package com.dasoops.common.config.serializer

import com.dasoops.common.entity.enums.database.IDbColumnEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

open class IDbColumnEnumToStringConvertFactory : ConverterFactory<IDbColumnEnum, String> {
    override fun <T : String> getConverter(targetType: Class<T>): Converter<IDbColumnEnum, T> {
        return IDbColumnEnumToStringConvert()
    }

    private class IDbColumnEnumToStringConvert<T> : Converter<IDbColumnEnum, T> {
        override fun convert(source: IDbColumnEnum): T {
            return source.dbValue.toString() as T
        }
    }
}