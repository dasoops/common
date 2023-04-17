package com.dasoops.common.conf

import com.dasoops.common.entity.result.SimpleResult
import com.dasoops.common.exception.CustomException
import com.dasoops.common.exception.DataResolverException
import com.dasoops.common.exception.ProjectException
import com.dasoops.common.exception.ProjectExceptionEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanInstantiationException
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 全局异常处理程序
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/31
 * @see [BaseExceptionHandlerConfiguration]
 */
@RestControllerAdvice
abstract class BaseExceptionHandlerConfiguration {

    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * 消息解析异常处理
     *
     * @param e e
     */
    @ExceptionHandler(ProjectExceptionEntity::class)
    open fun catchProjectException(e: ProjectExceptionEntity): SimpleResult {
        log.error("项目内部异常: ", e)
        return SimpleResult.fail(e.exceptionEnum)
    }

    /**
     * 消息解析异常处理
     *
     * @param e e
     */
    @ExceptionHandler(BeanInstantiationException::class, HttpRequestMethodNotSupportedException::class, BindException::class)
    open fun catchBeanInstantiationException(e: Exception): SimpleResult {
        log.error("参数解析异常: ", e)
        return SimpleResult.fail(DataResolverException.PARAM_ERROR)
    }

    /**
     * 自定义异常处理
     *
     * @param e e
     */
    @ExceptionHandler(CustomException::class)
    open fun catchCustomException(e: CustomException): SimpleResult {
        log.error("catch CustomException: ", e)
        return SimpleResult.fail(e.exceptionEnum)
    }

    /**
     * 未捕获运行时异常处理
     *
     * @param e e
     */
    @ExceptionHandler(RuntimeException::class)
    open fun exceptionHandler(e: RuntimeException): SimpleResult {
        log.error("catch RuntimeException: ", e)
        return SimpleResult.fail(ProjectException.UN_EXPECTED)
    }

    /**
     * 未捕获异常处理
     *
     * @param e e
     */
    @ExceptionHandler(Exception::class)
    open fun exceptionHandler(e: Exception): SimpleResult {
        log.error("catch Exception: ", e)
        return SimpleResult.fail(ProjectException.UN_EXPECTED)
    }
}