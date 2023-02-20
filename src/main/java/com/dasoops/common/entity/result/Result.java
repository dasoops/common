package com.dasoops.common.entity.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title Result
 * @classPath com.dasoops.dasq.common.entity.Result
 * @author DasoopsNicole@Gmail.com
 * @date 2022/10/07
 * @version 1.0.0
 * @description 通用返回结果集
 * @see BaseResult
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "通用返回结果集", description = "通用返回结果集")
public class Result<T> extends BaseResult {

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据", notes = "返回数据", example = "{\"id\":1,\"name\":\"A\"}", required = true)
    private T data;


    /**
     * 通用成功
     *
     * @return {@link BaseResult}
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.setMsg("请求成功");
        return result;
    }

    /**
     * 通用成功
     *
     * @param t data
     * @return {@link Result}
     */
    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.setMsg("请求成功");
        result.setData(t);
        return result;
    }

    /**
     * 通用成功
     *
     * @param t data
     * @return {@link Result}
     */
    public static <T> Result<T> success(String msg, T t) {
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(t);
        return result;
    }

    /**
     * 通用失败
     *
     * @param msg msg
     * @return {@link BaseResult}
     */
    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<T>();
        result.setCode(400);
        result.setMsg(msg);
        return result;
    }

}
