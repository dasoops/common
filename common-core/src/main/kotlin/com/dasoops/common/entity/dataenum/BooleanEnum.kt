package com.dasoops.common.entity.dataenum

/**
 * 布尔枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [BooleanEnum]
 */
enum class BooleanEnum(
    override val value: Int,
    val bool: Boolean
) : ApiEnum {
    TRUE(1, true),
    FALSE(0, false),
    ;

    companion object {
        fun by(value: Int): BooleanEnum {
            return if (value == 1) {
                TRUE
            } else {
                FALSE
            }
        }

        fun by(booleanValue: Boolean): BooleanEnum {
            return if (booleanValue) {
                TRUE
            } else {
                FALSE
            }
        }
    }
}