package com.dasoops.common.exception

import com.dasoops.common.entity.enums.exception.IExceptionEnum

/**
 * 项目通用异常枚举(200xx)
 * @title: RuntimeDataExceptionEnum
 * @classPath com.herdsman.wgts.api.originalData.RuntimeDataExceptionEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/04
 * @version 1.0.0
 * @see [ProjectExceptionEnum]
 */
enum class ProjectExceptionEnum(private val msg: String) : IExceptionEnum {
    NO_AUTH("没有权限这样操作"),
    UN_EXPECTED("预期外的异常"),
    NO_RECORD("没有查询到数据"),
    ;

    override fun getCode(): Int {
        return 20000 + ordinal
    }

    override fun getMsg(): String {
        return msg
    }

    override fun getException(): ProjectException {
        return ProjectException(this)
    }
}

open class ProjectException(exceptionEnum: ProjectExceptionEnum) : CustomException(exceptionEnum)

object UnexpectedException : CustomException(ProjectExceptionEnum.UN_EXPECTED)
object NoRecordException : CustomException(ProjectExceptionEnum.NO_RECORD)
object NoAuthException : CustomException(ProjectExceptionEnum.NO_AUTH)