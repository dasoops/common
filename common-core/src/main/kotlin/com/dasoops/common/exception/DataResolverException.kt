package com.dasoops.common.exception

import com.dasoops.common.IException
import com.dasoops.common.exception.ProjectExceptionEntity

/**
 * 数据解析器异常枚举(206xx)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/25
 * @see [DataResolverException]
 */
enum class DataResolverException(override val message: String) : IException {
    PARAMETER_RESLOVE_ERROR("输入参数解析异常"),
    PARAM_ERROR("参数错误,请参照接口文档输入"),
    OUT_OF_BOUNDS("参数超出范围"),
    ;

    inner class DataResloverExceptionEntity : ProjectExceptionEntity(this)

    override val code: Int = 20600 + ordinal
    override fun get() = DataResloverExceptionEntity()
}