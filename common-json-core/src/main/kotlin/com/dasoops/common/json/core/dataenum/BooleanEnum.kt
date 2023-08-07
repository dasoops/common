package com.dasoops.common.json.core.dataenum;

/**
 * 布尔枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/06/26
 * @see [BooleanEnum]
 */
enum class BooleanEnum(
        override val data: Int
) : IntDataEnum {
    TRUE(1),
    FALSE(0),
    ;

    override fun getValue(): Int {
        return data;
    }

    fun bool(): Boolean {
        return this == BooleanEnum.TRUE
    }

    companion object{
        fun of(bool: Boolean): BooleanEnum = if (bool) TRUE else FALSE
    }
}

