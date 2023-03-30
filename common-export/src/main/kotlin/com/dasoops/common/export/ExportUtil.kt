package com.dasoops.common.export

import cn.hutool.http.ContentType
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletResponse

/**
 * 导出工具
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/25
 */
object ExportUtil {

    /**
     * 设置响应
     * @param [response] 响应
     * @param [fileName] 文件名称
     * @param [contentType] 内容类型
     */
    fun setResponse(response: HttpServletResponse, fileName: String, fileTypeSuffix: String, contentType: String) {
        /**
         * xlsx的ContentType
         */
        //文件编码,类型
        response.contentType = contentType
        response.characterEncoding = "utf-8"
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition")

        //文件名
        val encodeFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("\\+".toRegex(), "%20")
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''$encodeFileName.$fileTypeSuffix")
    }

    /**
     * 设置响应
     * @param [response] 响应
     * @param [fileName] 文件名称
     * @param [contentType] 内容类型
     */
    fun setResponse(response: HttpServletResponse, fileName: String, fileTypeSuffix: String, contentType: ContentType) {
        setResponse(response, fileName, fileTypeSuffix, contentType.value)
    }

}