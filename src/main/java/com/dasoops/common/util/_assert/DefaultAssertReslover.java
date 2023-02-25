package com.dasoops.common.util._assert;


import com.dasoops.common.exception.CustomException;

/**
 * @title DefaultAssertReslover
 * @classPath com.dasoops.common.util.aoe.DefaultAssertReslover
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/31
 * @version 1.0.0
 * @description 默认断言解析器
 * @see AssertReslover
 */
public class DefaultAssertReslover implements AssertReslover {

    @Override
    public void allMustNull() {
        throw new CustomException(AssertExceptionEnum.PARAMETER_IS_NULL);
    }

    @Override
    public void allMustNotNull() {
        throw new CustomException(AssertExceptionEnum.PARAMETER_NOT_NULL);
    }

    @Override
    public void isTrue() {
        throw new CustomException(AssertExceptionEnum.IS_TRUE);
    }

    @Override
    public void isFalse() {
        throw new CustomException(AssertExceptionEnum.IS_FALSE);
    }

    @Override
    public void dbExecuteMustSuccess() {
        throw new CustomException(AssertExceptionEnum.DB_EXECUTE_FAILED);
    }

    @Override
    public void dbExecuteReturnMustNotNull() {
        throw new CustomException(AssertExceptionEnum.DB_EXECUTE_RETURN_NOT_NULL);
    }

    @Override
    public void dbExecuteResNotZero() {
        throw new CustomException(AssertExceptionEnum.DB_EXECUTE_RETURN_NOT_ZERO);
    }
}
