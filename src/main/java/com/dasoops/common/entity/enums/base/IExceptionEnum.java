package com.dasoops.common.entity.enums.base;

/**
 * @title IExceptionEnum
 * @classPath com.dasoops.common.entity.enums.base.IExceptionEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/14
 * @version 1.0.0
 * @description iexception枚举
 */
public interface IExceptionEnum {

    /**
     * 获取错误代码
     *
     * @return {@link Integer}
     */
    Integer getCode();

    /**
     * 获取错误描述
     *
     * @return {@link String}
     */
    String getMsg();

}
