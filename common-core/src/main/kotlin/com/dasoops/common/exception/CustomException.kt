package com.dasoops.common.exception

import cn.hutool.core.exceptions.ExceptionUtil
import com.dasoops.common.IException

/**
 * 自定义异常
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/01
 * @see [CustomException]
 */
open class CustomException(
    val exception: IException = ProjectException.UN_EXPECTED,
) : RuntimeException(exception.get()) {

    override val message = exception.message

    val info: String = """
            [${exception.code}:${exception.message}]: 
            ${exception.get().getStackInfo()}
            """.trimIndent()

    open fun getStackInfo(): String = ExceptionUtil.stacktraceToString(this)
}