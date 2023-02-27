package com.dasoops.common.config.serializer

import com.dasoops.common.entity.enums.database.IDbColumnEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

open class IDbColumnEnumToIntConvertFactory : ConverterFactory<IDbColumnEnum, Int> {
    override fun <T : Int> getConverter(targetType: Class<T>): Converter<IDbColumnEnum, T> {
        return IDbColumnEnumToIntConvert()
    }

    private class IDbColumnEnumToIntConvert<T> : Converter<IDbColumnEnum, T> {
        override fun convert(source: IDbColumnEnum): T {
            return source.dbValue as T
        }
    }
}