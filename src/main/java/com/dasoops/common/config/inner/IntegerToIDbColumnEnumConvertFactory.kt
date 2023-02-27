package com.dasoops.common.config.inner

import com.dasoops.common.entity.enums.database.IDbColumnEnum
import com.dasoops.common.exception.DataResolverExceptionEnum
import com.dasoops.common.util.dbcolumn.DbColumnUtil
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

/**
 * 整形 转 数据库枚举 工厂
 * @title: IntegerToIDbColumnEnumConvertFactory
 * @classPath com.dasoops.common.config.inner.IntegerToIDbColumnEnumConvertFactory
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [IntegerToIDbColumnEnumConvertFactory]
 */
open class IntegerToIDbColumnEnumConvertFactory : ConverterFactory<Int, IDbColumnEnum> {
    override fun <T : IDbColumnEnum> getConverter(targetType: Class<T>): Converter<Int, T> {
        return IntegerToIDbColumnEnumConvert(targetType)
    }

    private class IntegerToIDbColumnEnumConvert<T : IDbColumnEnum>(private val enumType: Class<out T>) : Converter<Int, T> {
        override fun convert(source: Int): T {
            return DbColumnUtil.getBy(enumType, source) ?: throw DataResolverExceptionEnum.PARAMETER_RESLOVE_ERROR.exception
        }
    }
}