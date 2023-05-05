package com.dasoops.common.entity.result

import io.swagger.v3.oas.annotations.media.Schema

/**
 * @title Result
 * @classPath com.dasoops.dasq.common.entity.Result
 * @author DasoopsNicole@Gmail.com
 * @date 2022/10/07
 * @version 1.0.0
 * @description 通用返回结果集
 * @see AbstractResult
 */
@Schema(description = "通用返回结果集")
open class Result<T>(
    /**
     * 返回数据
     */
    @field:Schema(description = "返回数据", required = true)
    val data: T
) : AbstractResult(success, "请求成功") {

    companion object {
        const val success = 200
        fun <T> success(t: T) = Result(t)
    }
}