package com.dasoops.common.db.entity.result

import com.dasoops.common.IException
import io.swagger.annotations.ApiModel

/**
 * @title Result
 * @classPath com.dasoops.dasq.common.entity.Result
 * @author DasoopsNicole@Gmail.com
 * @date 2022/10/07
 * @version 1.0.0
 * @description 简单返回结果集
 * @see AbstractResult
 */
@ApiModel(value = "简单返回结果集", description = "简单返回结果集")
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