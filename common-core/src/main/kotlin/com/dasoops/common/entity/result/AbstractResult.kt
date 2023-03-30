package com.dasoops.common.entity.result

import com.dasoops.common.IResult
import io.swagger.annotations.ApiModelProperty

/**
 * @title BaseResult
 * @classPath com.dasoops.common.entity.result.BaseResult
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/14
 * @version 1.0.0
 * @description 返回结果集基类
 * @see IResult
 */
abstract class AbstractResult(
    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应码", notes = "响应码", example = "200", required = true)
    override val code: Int,

    /**
     * 响应信息
     */
    @ApiModelProperty(value = "响应信息", notes = "响应信息", example = "请求成功", required = true)
    override val msg: String,
) : IResult