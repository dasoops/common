package com.dasoops.common.config;

import com.dasoops.common.entity.enums.ExceptionEnum;
import com.dasoops.common.entity.enums.base.IExceptionEnum;
import com.dasoops.common.entity.result.SimpleResult;
import com.dasoops.common.exception.LogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title GlobalExceptionHandler
 * @classPath com.dasoops.dasserver.core.GlobalExceptionHandler
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/31
 * @version 1.0.0
 * @description 全局异常处理程序
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class BaseExceptionHandler {
    /**
     * 逻辑异常处理
     *
     * @param e e
     */
    @ExceptionHandler(LogicException.class)
    public SimpleResult catchLogicException(LogicException e) {
        log.error("catch LogicException: ", e);
        return SimpleResult.fail(e.getExceptionEnum());
    }
    /**
     * 逻辑异常处理
     *
     * @param e e
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public SimpleResult catchHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("catch LogicException: ", e);
        return SimpleResult.fail(ExceptionEnum.PARAMETER_RESLOVE_ERROR);
    }

    /**
     * 逻辑异常处理
     *
     * @param e e
     */
    @ExceptionHandler(Exception.class)
    public SimpleResult exceptionHandler(Exception e) {
        log.error("catch Exception: ", e);
        return SimpleResult.fail(ExceptionEnum.UN_EXPECTED);
    }
}
