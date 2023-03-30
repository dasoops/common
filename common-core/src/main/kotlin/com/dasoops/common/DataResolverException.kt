package com.dasoops.common

import com.dasoops.common.exception.ProjectExceptionEntity

/**
 * 数据解析器异常枚举(100xx)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/25
 * @see [DataResolverException]
 */
enum class DataResolverException(override val message: String) : IException {
    PARAMETER_RESLOVE_ERROR("输入参数解析异常"),
    MISSING_REQUIRED_PARAM("缺少必填参数"),
    OUT_OF_BOUNDS("参数超出范围"),
    ;

    inner class DataResloverExceptionEntity : ProjectExceptionEntity(this)

    override val code: Int = 10000 + ordinal
    override fun get() = DataResloverExceptionEntity()
}