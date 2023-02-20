package com.dasoops.common.entity.enums.base;

import com.dasoops.common.entity.enums.DbColumnEnumExceptionEnum;
import com.dasoops.common.exception.LogicException;

import java.util.Arrays;
import java.util.List;

/**
 * @title IDbColumnEnum
 * @classPath com.dasoops.common.entity.enums.base.IDbColumnEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/02
 * @version 1.0.0
 * @description 数据库字段枚举
 */
public interface IDbColumnEnum {

    /**
     * 获取数据库值
     *
     * @return {@link Integer}
     */
    Integer getDbValue();

    /**
     * 获取所有可能值
     *
     * @param clazz clazz
     * @return {@link List}<{@link Integer}>
     */
    static List<Integer> getAllProbableValue(Class<? extends IDbColumnEnum> clazz) {
        if (!clazz.isEnum()) {
            throw new LogicException(DbColumnEnumExceptionEnum.NOT_ENUM);
        }

        IDbColumnEnum[] enumConstantArr = clazz.getEnumConstants();
        return Arrays.stream(enumConstantArr).map(IDbColumnEnum::getDbValue).toList();
    }

}
