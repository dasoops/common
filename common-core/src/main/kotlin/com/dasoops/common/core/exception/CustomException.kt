package com.dasoops.common.core.exception

import cn.hutool.core.exceptions.ExceptionUtil
import com.dasoops.common.core.IExceptionEnum

/**
 * 自定义异常
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/01
 * @see [CustomException]
 */
open class CustomException(
    val exceptionEnum: IExceptionEnum = ProjectException.UN_EXPECTED,
    final override val message: String = exceptionEnum.message
) : RuntimeException() {

    val info: String = """
            [${exceptionEnum.code}:$message]: 
            ${this.getStackInfo()}
            """.trimIndent()

    open fun getStackInfo(): String = ExceptionUtil.stacktraceToString(this)
}