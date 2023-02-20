package com.dasoops.common.entity.enums;

import com.dasoops.common.entity.enums.base.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title ExceptionCodeEnum
 * @classPath com.dasoops.dasq.common.exception.entity.enums.ExceptionCodeEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2022/10/08
 * @version 1.0.0
 * @description 异常编号枚举(0-9999,65535)
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
    IS_TRUE(2001, "值必须为true"),
    IS_FALSE(2002, "值必须为false"),
    SIZE_ZERO(2003, "数据不可为空"),
    UNKNOWN_KEYWORD(2004, "未知的关键词"),

    /**
     * 参数异常
     */
    PARAMETER_NOT_NULL(3001, "参数不可为空"),
    PARAMETER_IS_NULL(3002, "参数必须为空"),
    PARAMETER_OUT_OF_SCOPE(3003, "超出界定范围的参数"),
    PARAMETER_RESLOVE_ERROR(3004, "输入参数解析异常"),

    /**
     * 数据库异常
     */
    DB_CONNECTION_ERROR(4001, "数据库连接异常"),
    DB_EXECUTE_FAILED(4002, "数据库操作失败"),
    DB_EXECUTE_RETURN_NOT_ZERO(4003, "数据库执行生效记录数为0"),
    DB_EXECUTE_RETURN_NOT_NULL(4004, "数据库返回不应为null"),
    DB_EXECUTE_RETURN_NOT_FALSE(4005, "数据库返回不应为false"),

    /**
     * 缓存异常
     */
    REDIS_DATA_NOT_NULL(5001, "redis数据不应为空"),

    /**
     * 系统异常
     */
    TYPE_CONVERT(7001, "类型转换异常"),
    INIT_ERROR(7002, "项目初始化异常"),
    NO_NULL_PARAMETER_CONSTRUCTOR(7003, "没有无参构造"),

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

}
