package com.dasoops.common.code.entity;

import com.dasoops.common.code.CodeException;

/**
 * 实体类型
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [EntityType]
 */
enum class EntityType {
    INT, STRING, ARRAY, OBJECT;

    fun of(value: String): EntityType {
        return when (value) {
            "integer" -> INT
            "string" -> STRING
            "array" -> ARRAY
            "object" -> OBJECT
            else -> throw CodeException.UNDEFINED_ENTITY_TYPE.get()
        }
    }
}