package com.dasoops.common.entity.enums.exception;

import com.dasoops.common.exception.CustomException;

/**
 * @title IExceptionEnum
 * @classPath com.dasoops.common.entity.enums.exception.IExceptionEnum
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

    /**
     * 获取异常
     *
     * @return {@link CustomException}
     */
    CustomException getException();

}
