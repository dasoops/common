package com.dasoops.common.config;

import com.dasoops.common.entity.enums.exception.ExceptionEnum;
import com.dasoops.common.entity.result.Result;
import com.dasoops.common.entity.result.SimpleResult;
import com.dasoops.common.entity.vo.base.BaseInnerVo;
import com.dasoops.common.entity.vo.base.SimplePageVo;
import com.dasoops.common.exception.CustomException;
import com.dasoops.common.exception.DataResolverExceptionEnum;
import com.dasoops.common.exception.NoRecordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title GlobalExceptionHandler
 * @classPath com.dasoops.dasserver.core.GlobalExceptionHandler
 * @date 2022/12/31
 * @description 全局异常处理程序
 */
@Slf4j
@RestControllerAdvice
public abstract class BaseExceptionHandler {
    /**
     * 自定义异常处理
     *
     * @param e e
     */
    @ExceptionHandler(NoRecordException.class)
    public SimpleResult catchNoRecordException(NoRecordException e) {
        return SimpleResult.fail(ExceptionEnum.NO_RECORD);
    }

    /**
     * 消息解析异常处理
     *
     * @param e e
     */
    @ExceptionHandler(BeanInstantiationException.class)
    public SimpleResult catchBeanInstantiationException(BeanInstantiationException e) {
        log.error("缺少必填参数: ", e);
        return SimpleResult.fail(DataResolverExceptionEnum.MISSING_REQUIRED_PARAM);
    }

    /**
     * 消息解析异常处理
     *
     * @param e e
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public SimpleResult catchHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("catch catchHttpRequestMethodNotSupportedException: ", e);
        return SimpleResult.fail(ExceptionEnum.REQUEST_METHOD_NOT_SUPPORTED);
    }

    /**
     * 消息解析异常处理
     *
     * @param e e
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public SimpleResult catchHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("catch catchHttpMessageNotReadableException: ", e);
        return SimpleResult.fail(DataResolverExceptionEnum.PARAMETER_RESLOVE_ERROR);
    }

    /**
     * 逻辑异常处理
     *
     * @param e e
     */
    @ExceptionHandler(BindException.class)
    public SimpleResult catchBindException(BindException e) {
        log.error("catch catchBindException: ", e);
        return SimpleResult.fail(DataResolverExceptionEnum.MISSING_REQUIRED_PARAM);
    }

    /**
     * 自定义异常处理
     *
     * @param e e
     */
    @ExceptionHandler(CustomException.class)
    public SimpleResult catchLogicException(CustomException e) {
        log.error("catch CustomException: ",  e);
        return SimpleResult.fail(e.getExceptionEnum());
    }

    /**
     * 未捕获异常处理
     *
     * @param e e
     */
    @ExceptionHandler(Exception.class)
    public SimpleResult exceptionHandler(Exception e) {
        log.error("catch Exception: ", e);
        return SimpleResult.fail(ExceptionEnum.UN_EXPECTED);
    }
}
