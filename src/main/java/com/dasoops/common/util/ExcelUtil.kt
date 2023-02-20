package com.dasoops.common.util

import com.alibaba.excel.EasyExcel
import com.alibaba.excel.ExcelWriter
import com.alibaba.excel.write.metadata.WriteSheet
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy
import com.dasoops.common.exception.LogicException
import com.dasoops.common.util.entity.ExportExceptionEnum
import com.dasoops.common.util.entity.ExportInfo
import org.slf4j.LoggerFactory
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
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
         * @param [fileName] 文件名称
         */

        @JvmSynthetic
        inline fun <reified T> ktSimpleExport(response: HttpServletResponse, dataList: List<T>, fileName: String) {
            if (dataList.isEmpty()) {
                throw LogicException(ExportExceptionEnum.DATA_NULL)
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
                throw LogicException(ExportExceptionEnum.DATA_NULL);
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
            setResponse(response, fileName)

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
                throw LogicException(ExportExceptionEnum.URL_ENCODER_ERROR)
            } catch (e: IOException) {
                log.error("io异常: ", e)
                throw LogicException(ExportExceptionEnum.DOWNLOAD_ERROR)
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

        /**
         * 设置响应
         * @param [response] 响应
         * @param [fileName] 文件名称
         */
        private fun setResponse(response: HttpServletResponse, fileName: String) {
            /**
             * xlsx的ContentType
             */
            //文件编码,类型
            response.contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            response.characterEncoding = "utf-8"
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition")

            //文件名
            val encodeFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("\\+".toRegex(), "%20")
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''$encodeFileName.xlsx")

        }
    }

}