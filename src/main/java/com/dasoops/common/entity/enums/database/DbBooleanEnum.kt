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
enum class DbBooleanEnum(override val dbValue:Int) : IDbColumnEnum {
    //
    TRUE(0),
    FALSE(1),
    ;

    companion object {
        fun getBy(dbValue: Int): DbBooleanEnum {
            return when (dbValue) {
                1 -> TRUE
                0 -> FALSE
                else -> throw DbColumnExceptionEnum.UNDEFINEND_VALUE.exception
            }
        }

        fun getBy(booleanValue: Boolean): DbBooleanEnum {
            return if (booleanValue) TRUE else FALSE
        }
    }
}