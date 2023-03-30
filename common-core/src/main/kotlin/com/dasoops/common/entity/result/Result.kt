package com.dasoops.common.entity.result

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @title Result
 * @classPath com.dasoops.dasq.common.entity.Result
 * @author DasoopsNicole@Gmail.com
 * @date 2022/10/07
 * @version 1.0.0
 * @description 通用返回结果集
 * @see AbstractResult
 */
@ApiModel(value = "通用返回结果集", description = "通用返回结果集")
class Result<T>(
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据", notes = "返回数据", required = true)
    val data: T
) : AbstractResult(200, "请求成功") {

    companion object {
        fun <T> success(t: T) = Result(t)
    }
}