package com.dasoops.common.util.entity;


import com.dasoops.common.entity.enums.ExceptionEnum;
import com.dasoops.common.exception.LogicException;

/**
 * @title DefaultAssertReslover
 * @classPath com.dasoops.common.util.entity.DefaultAssertReslover
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/31
 * @version 1.0.0
 * @description 默认断言解析器
 * @see AssertReslover
 */
public class DefaultAssertReslover implements AssertReslover {

    @Override
    public void allMustNull() {
        throw new LogicException(ExceptionEnum.PARAMETER_IS_NULL);
    }

    @Override
    public void allMustNotNull() {
        throw new LogicException(ExceptionEnum.PARAMETER_NOT_NULL);
    }

    @Override
    public void isTrue() {
        throw new LogicException(ExceptionEnum.IS_TRUE);
    }

    @Override
    public void isFalse() {
        throw new LogicException(ExceptionEnum.IS_FALSE);
    }

    @Override
    public void dbExecuteMustSuccess() {
        throw new LogicException(ExceptionEnum.DB_EXECUTE_FAILED);
    }

    @Override
    public void dbExecuteReturnMustNotNull() {
        throw new LogicException(ExceptionEnum.DB_EXECUTE_RETURN_NOT_NULL);
    }

    @Override
    public void dbExecuteResNotZero() {
        throw new LogicException(ExceptionEnum.DB_EXECUTE_RETURN_NOT_ZERO);
    }
}
