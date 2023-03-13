package com.dasoops.common.entity.enums.exception;

import com.dasoops.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title ExceptionCodeEnum
 * @classPath com.dasoops.dasq.common.exception.entity.enums.ExceptionCodeEnum
 * @date 2022/10/08
 * @description 异常编号枚举(0 - 9999, 65535)
 * @see Enum
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum implements IExceptionEnum {

    /**
     * 预料外的异常
     */
    UN_EXPECTED(65535, "预料之外的错误"),

    /**
     * 通用异常
     */
    NO_RECORD(2001, "没有查询到数据"),
    PARAMETER_NOT_NULL(2002, "参数不可为空"),
    SIZE_ZERO(2003, "数据不可为空"),
    UNKNOWN_KEYWORD(2004, "未知的关键词"),

    /**
     * 数据库异常
     */
    DB_CONNECTION_ERROR(4001, "数据库连接异常"),

    /**
     * 缓存异常
     */
    REDIS_DATA_NOT_NULL(5001, "redis数据不应为空"),

    /**
     * 系统异常
     */
    TYPE_CONVERT(7001, "类型转换异常"),
    INIT_ERROR(7002, "项目初始化异常"),
    REQUEST_METHOD_NOT_SUPPORTED(7003, "不支持的请求方法"),

    /**
     * other
     */
    TEST_EXCEPTION(8001, "这是一个测试异常"),
    CRAZY_TEST_EXCEPTION(8002, "CrazyThursDayPayMe50"),
    ;

    /**
     * 错误代码
     */
    private final Integer code;

    /**
     * 错误描述
     */
    private final String msg;

    @Override
    public CustomException get() {
        return new CustomException(this);
    }
}
