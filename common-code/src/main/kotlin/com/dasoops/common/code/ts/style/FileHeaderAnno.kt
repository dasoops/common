package com.dasoops.common.code.ts.style

import cn.hutool.core.date.DatePattern
import cn.hutool.core.date.DateUtil

object FileHeaderAnno {

    fun `for`(name: String, another: String, isRequest: Boolean) = """
        |/**
        | * 对[$name]分组的${fileDescription(isRequest)}
        | * @author $another
        | * @date ${date()}
        | */
        | 
    """.trimMargin()

    private fun date() = DateUtil.date().toString(DatePattern.NORM_DATETIME_MINUTE_FORMAT)

    private fun fileDescription(isRequest: Boolean) = if (isRequest) {
        "实体类声明代码文件"
    } else {
        "请求代码文件"
    }
}