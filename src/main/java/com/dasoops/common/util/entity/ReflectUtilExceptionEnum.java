package com.dasoops.common.util.entity;

import com.dasoops.common.entity.enums.base.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title ReflectExceptionEnum
 * @classPath com.dasoops.common.entity.enums.ReflectExceptionEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/08
 * @version 1.0.0
 * @description 反射工具类异常枚举(900xx)
 * @see Enum
 * @see IExceptionEnum
 */
@AllArgsConstructor
@Getter
public enum ReflectUtilExceptionEnum implements IExceptionEnum {
    /**
     * eem快速生成
     */
    INSTANCE_ERROR("类创建失败"),
    NO_SUCH_CONSTRUCTOR("未找到无参构造"),
    CANT_INVOKE_METHOD("无法访问需执行方法"),
    NO_SUCH_SET_METHOD("未找到字段对应set方法"),
    CANT_RESLOVE_PARAM("不可解析的参数类型"),
    PARAMETER_RESLOVE_CONVERT_ERROR("参数解析时发生类型转换异常"),
    ;


    @Override
    public Integer getCode() {
        return 90000 + ordinal();
    }

    final String msg;

    @Override
    public String getMsg() {
        return msg;
    }
}
