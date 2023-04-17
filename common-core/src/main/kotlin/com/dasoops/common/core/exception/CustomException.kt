package com.dasoops.common.exception

import cn.hutool.core.exceptions.ExceptionUtil
import com.dasoops.common.core.IException

/**
 * 自定义异常
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/01
 * @see [CustomException]
 */
open class CustomException(
    val exceptionEnum: IException = ProjectException.UN_EXPECTED,
) : RuntimeException() {

    override val message = exceptionEnum.message

    val info: String = """
            [${exceptionEnum.code}:${exceptionEnum.message}]: 
            ${this.getStackInfo()}
            """.trimIndent()

    open fun getStackInfo(): String = ExceptionUtil.stacktraceToString(this)
}