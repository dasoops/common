package com.dasoops.common.entity.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @title BaseResult
 * @classPath com.dasoops.common.entity.result.BaseResult
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/14
 * @version 1.0.0
 * @description 返回结果集基类
 * @see IResult
 */
public abstract class BaseResult implements IResult {

    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应码", notes = "响应码", example = "200", required = true)
    public Integer code;

    /**
     * 响应信息
     */
    @ApiModelProperty(value = "响应信息", notes = "响应信息", example = "请求成功", required = true)
    public String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
