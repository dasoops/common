package com.dasoops.common.export

import com.dasoops.common.IException
import com.dasoops.common.exception.CustomException

/**
 * 导出异常(901xx)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [ExportException]
 */
enum class ExportException(override val message: String) : IException {
    DATA_NULL("导出数据为空"),
    URL_ENCODER_ERROR("导出部分url转码错误"),
    DOWNLOAD_ERROR("文件下载失败,请重试"),
    ;

    inner class ExportExceptionEntity : CustomException(this)

    override val code = 90100 + ordinal
    override fun get() = ExportExceptionEntity()
}