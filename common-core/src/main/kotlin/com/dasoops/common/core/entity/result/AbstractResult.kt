package com.dasoops.common.core.entity.result

import com.dasoops.common.core.IResult
import io.swagger.v3.oas.annotations.media.Schema

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
    @field:Schema(description = "响应码", example = "200", required = true)
    override val code: Int,

    /**
     * 响应信息
     */
    @field:Schema(description = "响应信息", example = "请求成功", required = true)
    override val msg: String,
) : IResult