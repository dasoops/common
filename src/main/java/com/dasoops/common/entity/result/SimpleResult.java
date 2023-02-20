package com.dasoops.common.entity.result;

import com.dasoops.common.entity.enums.exception.IExceptionEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title Result
 * @classPath com.dasoops.dasq.common.entity.Result
 * @author DasoopsNicole@Gmail.com
 * @date 2022/10/07
 * @version 1.0.0
 * @description 简单返回结果集
 * @see BaseResult
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "简单返回结果集", description = "简单返回结果集")
public class SimpleResult extends BaseResult {

    /**
     * 通用成功
     *
     * @return {@link BaseResult}
     */
    public static SimpleResult success() {
        SimpleResult result = new SimpleResult();
        result.setCode(200);
        result.setMsg("请求成功");
        return result;
    }

    /**
     * 通用成功
     *
     * @param msg 返回信息
     * @return {@link SimpleResult}
     */
    public static SimpleResult success(String msg) {
        SimpleResult result = new SimpleResult();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    /**
     * 通用失败
     *
     * @param msg msg
     * @return {@link BaseResult}
     */
    public static SimpleResult fail(String msg) {
        SimpleResult result = new SimpleResult();
        result.setCode(400);
        result.setMsg(msg);
        return result;
    }

    /**
     * 通用失败
     *
     * @param msg msg
     * @return {@link BaseResult}
     */
    public static SimpleResult fail(Integer code, String msg) {
        SimpleResult result = new SimpleResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 通用失败
     *
     * @param exceptionEnum 异常枚举
     * @return {@link SimpleResult}
     */
    public static SimpleResult fail(IExceptionEnum exceptionEnum) {
        SimpleResult result = new SimpleResult();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        return result;
    }
}
