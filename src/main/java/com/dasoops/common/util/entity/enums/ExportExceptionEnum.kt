package com.dasoops.common.util.entity.enums

import com.dasoops.common.entity.enums.exception.IExceptionEnum
import com.dasoops.common.exception.CustomException

/**
 * @title ExportExceptionEnum
 * @classPath com.dasoops.common.util.entity.enums.ExportExceptionEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/30
 * @version 1.0.0
 * @description 导出异常枚举(901xx)
 * @see IExceptionEnum
 */
enum class ExportExceptionEnum(private val msg: String) : IUtilExceptionEnum {

    DATA_NULL("导出数据为空"),
    URL_ENCODER_ERROR("导出部分url转码错误"),
    DOWNLOAD_ERROR("文件下载失败,请重试"),
    ;

    override fun getCode(): Int {
        return 90100 + ordinal
    }

    override fun getMsg(): String {
        return msg
    }

    override fun getException(): ExportException {
        return ExportException()
    }

    inner class ExportException : UtilException(this)
}