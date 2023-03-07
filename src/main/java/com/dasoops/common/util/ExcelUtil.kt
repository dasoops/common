package com.dasoops.common.util

import com.alibaba.excel.EasyExcel
import com.alibaba.excel.ExcelWriter
import com.alibaba.excel.write.metadata.WriteSheet
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy
import com.dasoops.common.util.export.ExportExceptionEnum
import com.dasoops.common.util.export.ExportInfo
import com.dasoops.common.util.export.ExportUtil
import org.slf4j.LoggerFactory
import java.io.IOException
import java.io.UnsupportedEncodingException
import javax.servlet.http.HttpServletResponse

class ExcelUtil {

    companion object {

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
                throw ExportExceptionEnum.DATA_NULL.exception
            }
            val exportInfo = ExportInfo.ktBuild(dataList)

            simpleExport(response, exportInfo, fileName)
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
                throw ExportExceptionEnum.DATA_NULL.exception
            }
            simpleExport(response, ExportInfo.build(dataList), fileName)
        }


        /**
         * 简单导出(自动提取基类)
         *
         * @param [response] response
         * @param [fileName] 文件名称
         * @param [dto] dto
         */
        fun <T> simpleExport(response: HttpServletResponse, dto: ExportInfo<T>, fileName: String) {
            export(response, fileName, listOf(dto))
        }


        /**
         * 导出
         * @param [response] response
         * @param [fileName] 文件名
         * @param [dtoList] dto集合
         */
        fun export(response: HttpServletResponse, fileName: String, dtoList: List<ExportInfo<*>>) {
            ExportUtil.setResponse(response, fileName, "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")

            try {
                EasyExcel
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
                throw ExportExceptionEnum.URL_ENCODER_ERROR.exception
            } catch (e: IOException) {
                log.error("io异常: ", e)
                throw ExportExceptionEnum.DOWNLOAD_ERROR.exception
            }
        }

        /**
         * 写sheet
         * @param [writer] excelWriter
         * @param [exportInfo] 导出dto
         */
        private fun writeSheet(writer: ExcelWriter, exportInfo: ExportInfo<*>) {
            val (dataList, dataClass, sheetNo, sheetName) = exportInfo
            val writeSheet: WriteSheet = EasyExcel.writerSheet(sheetNo, sheetName).head(dataClass).build()
            writer.write(dataList, writeSheet)
        }
    }

}