package com.dasoops.common.exception;

import cn.hutool.core.util.StrUtil;
import com.dasoops.common.entity.enums.exception.IExceptionEnum;

/**
 * @title CqLogicException
 * @classPath com.dasoops.dasserver.cq.exception.CqLogicException
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/31
 * @version 1.0.0
 * @description 自定义逻辑异常异常
 * @see AbstractBaseCustomException
 */
public class CustomException extends AbstractBaseCustomException {

    public CustomException(IExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }

    public CustomException(Throwable exception) {
        super(exception);
    }

    public CustomException(IExceptionEnum exceptionEnum, String stackMessage) {
        super(exceptionEnum, stackMessage);
    }

    public CustomException(IExceptionEnum exceptionEnum, Exception e) {
        super(exceptionEnum, e);
    }

    /**
     * 得到当前堆栈信息
     *
     * @return {@link String}
     */
    @Override
    public String getStackInfo() {
        final int excludeLine = 0;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(StrUtil.format("ERROR{{}:{}}{}  stack\r\n", super.getExceptionEnum().getCode(), super.getExceptionEnum().getMsg(), super.getStackMessage() == null ? "" : super.getStackMessage()));
        //排除前5行(断言,异常类信息)
        for (int i = excludeLine; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            sb.append(StrUtil.format("\t at {}.{}({}:{})\r\n", element.getClassName(), element.getMethodName(), element.getClassName(), element.getLineNumber()));
        }
        return sb.toString();
    }

    /**
     * 得到当前堆栈信息
     *
     * @return {@link String}
     */
    @Override
    public String getStackInfo(Throwable e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        //未知异常情况
        sb.append(StrUtil.format("ERROR{{}:{}}{}, NestingExceptionMessage is: \"{}\", stack\r\n", super.getExceptionEnum().getCode(), super.getExceptionEnum().getMsg(), super.getStackMessage() == null ? "" : super.getStackMessage(), e.getMessage()));
        //排除前5行(断言,异常类信息)
        for (StackTraceElement element : stackTrace) {
            sb.append(StrUtil.format("\t at {}.{}({}:{})\r\n", element.getClassName(), element.getMethodName(), element.getClassName(), element.getLineNumber()));
        }
        return sb.toString();
    }
}
