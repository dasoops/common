package com.dasoops.common.entity.enums.param;

/**
 * @title ISortColumnEnum
 * @classPath com.dasoops.common.entity.enums.param.ISortColumnEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/12
 * @version 1.0.0
 * @description sort列枚举
 */
public interface ISortColumnEnum {

    /**
     * 获取交互值
     *
     * @return {@link Integer}
     */
    Integer getIntegerValue();

    /**
     * 获取字段名字
     *
     * @return {@link String}
     */
    String getColumnName();

}
