package com.dasoops.common.entity.enums.database

import com.dasoops.common.exception.DbColumnExceptionEnum
import lombok.AllArgsConstructor
import lombok.Getter

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title DbBooleanEnum
 * @classPath com.dasoops.common.entity.enums.database.DbBooleanEnum
 * @date 2023/02/23
 * @description 数据库boolean枚举
 * @see Enum
 *
 * @see IDbColumnEnum
 */
enum class DbBooleanEnum(override val dbValue: Int, val bool: Boolean) : IDbColumnEnum {
    //
    TRUE(0, true),
    FALSE(1, false),
    ;

    companion object {
        fun by(dbValue: Int): DbBooleanEnum {
            return if (dbValue == 1) {
                TRUE
            } else {
                FALSE
            }
        }

        fun by(booleanValue: Boolean): DbBooleanEnum {
            return if (booleanValue) {
                TRUE
            } else {
                FALSE
            }
        }
    }
}