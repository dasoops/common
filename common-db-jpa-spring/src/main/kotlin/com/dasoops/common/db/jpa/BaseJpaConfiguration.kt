package com.dasoops.common.db.jpa

import com.dasoops.common.core.entity.dataenum.BooleanEnum
import com.dasoops.common.core.entity.dataenum.DataEnum
import com.dasoops.common.exception.DataEnumException
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.hibernate.annotations.ConverterRegistration

abstract class BaseJpaConfiguration {
    @Converter(autoApply = true)
    open class BooleanEnumConverter : AttributeConverter<Int, BooleanEnum> {
        override fun convertToDatabaseColumn(value: Int): BooleanEnum {
            return BooleanEnum.by(value)
        }

        override fun convertToEntityAttribute(value: BooleanEnum): Int {
            return value.data
        }
    }

    @Converter(autoApply = true)
    open class DataEnumConverter : AttributeConverter<Int, DataEnum> {
        override fun convertToDatabaseColumn(value: Int): DataEnum {
            TODO()
        }

        override fun convertToEntityAttribute(value: DataEnum): Int {
            return value.data
        }
    }

    @Converter(autoApply = true)
    open class IsDeleteConverter : AttributeConverter<IsDelete, Int> {
        override fun convertToDatabaseColumn(value: IsDelete): Int {
            return value.data
        }

        override fun convertToEntityAttribute(value: Int): IsDelete {
            return when (value) {
                0 -> IsDelete.NO
                1 -> IsDelete.YES
                else -> throw DataEnumException.NOT_ENUM.get()
            }
        }
    }
}