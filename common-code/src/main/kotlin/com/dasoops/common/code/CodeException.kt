package com.dasoops.common.code;

import com.dasoops.common.IException
import com.dasoops.common.exception.ProjectExceptionEntity

/**
 * 代码生成器异常(208xx)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/06
 * @see [CodeException]
 */
enum class CodeException(override val message: String) : IException {

    UNDEFINED_ENTITY_TYPE("未定义的实体类类型"),
    UNDEFINED_CONVERT("未定义转换器")
    ;


    override val code: Int = 20800 + ordinal
    override fun get() = CodeExceptionEntity()

    inner class CodeExceptionEntity : ProjectExceptionEntity(this)

}