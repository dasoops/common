package com.dasoops.common.db.jpa

import com.dasoops.common.json.core.JsonException
import com.dasoops.common.json.core.dataenum.IntDataEnum
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

abstract class BaseJpaConfiguration {
    @Converter(autoApply = true)
    open class BooleanEnumConverter : AttributeConverter<Int, Boolean> {
        override fun convertToDatabaseColumn(value: Int): Boolean {
            return value == 1
        }

        override fun convertToEntityAttribute(value: Boolean): Int {
            return if (value) 1 else 0
        }
    }

    @Converter(autoApply = true)
    open class DataEnumConverter : AttributeConverter<Int, IntDataEnum> {
        override fun convertToDatabaseColumn(value: Int): IntDataEnum {
            TODO()
        }

        override fun convertToEntityAttribute(value: IntDataEnum): Int {
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
                else -> throw JsonException("不是一个枚举类")
            }
        }
    }
}