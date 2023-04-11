package com.dasoops.common.code.ts

import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.RandomUtil
import cn.hutool.core.util.ZipUtil
import com.dasoops.common.code.CodeFileWriter
import com.dasoops.common.code.entity.CodeFileZip
import com.dasoops.common.code.entity.CodeType
import com.dasoops.common.code.ts.style.TsStyle
import com.dasoops.common.util.ExportUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.FileCopyUtils
import javax.servlet.http.HttpServletResponse

/**
 * ts编号文件作家
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [TsCodeFileWriter]
 */
@Component
class TsCodeFileWriter : CodeFileWriter {
    override val writeType = CodeType.TS

    private val log = LoggerFactory.getLogger(javaClass)

    override fun write(response: HttpServletResponse, codeFileZip: CodeFileZip) {
        val (info, entityFileList, requestFileList) = codeFileZip
        val tmpDir = "${System.getProperty("java.io.tmpdir")}common-code/${RandomUtil.randomString(8)}"

        requestFileList?.forEach {
            val filePath = tmpDir + "/" + it.filePath.removePrefix("/") + it.fileName + ".ts"
            val file = FileUtil.touch(filePath)
            file.writeText(TsStyle.requestFile(it, info))
            log.debug("写入请求文件: $filePath")
        }

        entityFileList?.forEach {
            val filePath = tmpDir + "/" + it.filePath.removePrefix("/") + it.fileName + ".ts"
            val file = FileUtil.touch(filePath)
            file.writeText(TsStyle.entityFile(it, info))
            log.debug("写入实体类文件: $filePath")
        }

        val zip = ZipUtil.zip(tmpDir)
        ExportUtil.setResponse(response, "code", "zip", "application/x-zip-compressed")
        FileCopyUtils.copy(zip.inputStream(), response.outputStream)
        zip.delete()
        FileUtil.del(tmpDir)
    }
}