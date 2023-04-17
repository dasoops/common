package com.dasoops.common.core.entity.result

import com.dasoops.common.core.IException
import io.swagger.v3.oas.annotations.media.Schema

/**
 * @title Result
 * @classPath com.dasoops.dasq.common.entity.Result
 * @author DasoopsNicole@Gmail.com
 * @date 2022/10/07
 * @version 1.0.0
 * @description 简单返回结果集
 * @see AbstractResult
 */
@Schema(description = "简单返回结果集")
class SimpleResult(
    override val code: Int,
    override val msg: String
) : AbstractResult(code, msg) {
    companion object {
        fun success() = SimpleResult(200, "请求成功")
        fun success(msg: String) = SimpleResult(200, msg)
        fun fail(msg: String) = SimpleResult(400, msg)
        fun fail(exception: IException) = SimpleResult(exception.code, exception.message)
    }
}