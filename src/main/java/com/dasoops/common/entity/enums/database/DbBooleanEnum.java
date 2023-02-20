package com.dasoops.common.entity.enums.database;

import com.dasoops.common.exception.CustomException;
import com.dasoops.common.exception.DbColumnExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title DbBooleanConvertTinyintEnum
 * @classPath com.dasoops.common.entity.enums.base.DbBooleanConvertTinyintEnum
 * @date 2023/01/20
 * @description db布尔值枚举
 * @see Enum
 * @see IDbColumnEnum
 */
@AllArgsConstructor
public enum DbBooleanEnum implements IDbColumnEnum {

    /**
     * 真
     */
    TRUE(1),
    /**
     * 假
     */
    FALSE(0),
    ;

    @Getter
    final int dbValue;

    public static DbBooleanEnum getBy(Integer dbValue) {
        return switch (dbValue) {
            case 1 -> TRUE;
            case 0 -> FALSE;
            default -> throw DbColumnExceptionEnum.UNDEFINEND_VALUE.getException();
        };
    }

    public static DbBooleanEnum getBy(Boolean booleanValue) {
        return booleanValue ? TRUE : FALSE;
    }

}

