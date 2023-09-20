package com.dasoops.common.exception

import com.dasoops.common.IException

/**
 * 项目通用异常枚举(200xx)
 * @title: RuntimeDataExceptionEnum
 * @classPath com.herdsman.wgts.api.originalData.RuntimeDataExceptionEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/04
 * @version 1.0.0
 * @see [ProjectException]
 */
enum class ProjectException(override val message: String) : IException {
    NO_AUTH("没有权限这样操作"),
    NO_RECORD("没有查询到数据"),
    UN_EXPECTED("预期外的异常") {
        override val code: Int
            get() = 65535
    },
    ;

    override val code: Int = 20000 + ordinal
    override fun get() = ProjectExceptionEntity(this)

}

open class ProjectExceptionEntity(exceptionEnum: IException) : CustomException(exceptionEnum)

object UnExpectedException : ProjectExceptionEntity(ProjectException.UN_EXPECTED)
object NoRecordException : ProjectExceptionEntity(ProjectException.NO_RECORD)
object NoAuthException : ProjectExceptionEntity(ProjectException.NO_AUTH)