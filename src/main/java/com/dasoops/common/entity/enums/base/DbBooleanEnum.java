package com.dasoops.common.entity.enums.base;

import com.dasoops.common.entity.enums.DbColumnEnumExceptionEnum;
import com.dasoops.common.exception.LogicException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title DbBooleanConvertTinyintEnum
 * @classPath com.dasoops.common.entity.enums.base.DbBooleanConvertTinyintEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/20
 * @version 1.0.0
 * @description db布尔值枚举
 * @see Enum
 * @see IDbColumnEnum
 */
@AllArgsConstructor
@Getter
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

    final Integer dbValue;

    public static DbBooleanEnum getBy(Integer dbValue) {
        return switch (dbValue) {
            case 1 -> TRUE;
            case 0 -> FALSE;
            default -> throw new LogicException(DbColumnEnumExceptionEnum.UNDEFINEND_BOOLEAN_VALUE);
        };
    }

    public static DbBooleanEnum getBy(Boolean booleanValue) {
        return booleanValue ? TRUE : FALSE;
    }
}

