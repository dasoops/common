package com.dasoops.common.excel

import com.alibaba.excel.ExcelWriter
import com.alibaba.excel.write.metadata.WriteSheet
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy
import com.dasoops.common.core.ExportUtil
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import java.io.IOException
import java.io.UnsupportedEncodingException

object EasyExcel {
    private val log = LoggerFactory.getLogger(this::class.java);

    /**
     * 简单导出(自动提取基类)
     * 仅支持kt
     *
     * @param [response] response
     * @param [dataList] 数据集合
     */

    @JvmSynthetic
    inline fun <reified T> ktSimpleExport(response: HttpServletResponse, dataList: List<T>) {
        simpleExport(response, dataList, "default")
    }

    /**
     * 简单导出(自动提取基类)
     * 仅支持kt
     *
     * @param [response] response
     * @param [dataList] 数据集合
     * @param [fileName] 文件名称
     */

    @JvmSynthetic
    inline fun <reified T> ktSimpleExport(response: HttpServletResponse, dataList: List<T>, fileName: String) {
        if (dataList.isEmpty()) {
            throw ExcelException.DATA_NULL.get()
        }
        val excelInfo = ExcelInfo.ktBuild(dataList)

        simpleExport(response, excelInfo, fileName)
    }

    /**
     * 简单导出(自动提取基类)
     *
     * @param [response] response
     * @param [dataList] 数据集合
     * @param [fileName] 文件名称
     */

    fun <T> simpleExport(response: HttpServletResponse, dataList: List<T>, fileName: String) {
        if (dataList.isEmpty() || dataList[0] == null) {
            throw ExcelException.DATA_NULL.get()
        }
        simpleExport(response, ExcelInfo.build(dataList), fileName)
    }


    /**
     * 简单导出(自动提取基类)
     *
     * @param [response] response
     * @param [fileName] 文件名称
     * @param [dto] dto
     */
    fun <T> simpleExport(response: HttpServletResponse, dto: ExcelInfo<T>, fileName: String) {
        export(response, fileName, listOf(dto))
    }

    /**
     * 导出
     * @param [response] response
     * @param [fileName] 文件名
     * @param [dtoList] dto集合
     */
    fun export(response: HttpServletResponse, fileName: String, dtoList: List<ExcelInfo<*>>) {
        ExportUtil.setResponse(response, fileName, "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")

        try {
            com.alibaba.excel.EasyExcel
                .write(response.outputStream)
                .registerWriteHandler(LongestMatchColumnWidthStyleStrategy())
                .build()
                .use { writer ->
                    dtoList.forEach {
                        writeSheet(writer, it)
                    }
                }
        } catch (e: UnsupportedEncodingException) {
            log.error("导出部分url转码错误: ", e)
            throw ExcelException.URL_ENCODER_ERROR.get()
        } catch (e: IOException) {
            log.error("io异常: ", e)
            throw ExcelException.DOWNLOAD_ERROR.get()
        }
    }

    /**
     * 写sheet
     * @param [writer] excelWriter
     * @param [excelInfo] 导出dto
     */
    private fun writeSheet(writer: ExcelWriter, excelInfo: ExcelInfo<*>) {
        val (dataList, dataClass, sheetNo, sheetName) = excelInfo
        val writeSheet: WriteSheet = com.alibaba.excel.EasyExcel.writerSheet(sheetNo, sheetName).head(dataClass).build()
        writer.write(dataList, writeSheet)
    }
}