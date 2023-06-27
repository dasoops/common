package com.dasoops.common.json.core.dataenum;

/**
 * 布尔枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/06/26
 * @see [BooleanEnum]
 */
enum class BooleanEnum(
    override val data: java.lang.Integer
) : DataEnum<java.lang.Integer> {
    TRUE(1 as java.lang.Integer),
    FALSE(0 as java.lang.Integer),
    ;

    override fun getValue(): java.lang.Integer {
        return data;
    }

    fun bool(): Boolean {
        return this == BooleanEnum.TRUE
    }
}

