package com.dasoops.common.code

import cn.hutool.extra.spring.SpringUtil
import com.dasoops.common.code.entity.CodeFile
import com.dasoops.common.code.entity.CodeType
import javax.servlet.http.HttpServletResponse

/**
 * 代码文件writer
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [CodeFileWriter]
 */
interface CodeFileWriter {

    val writeType: CodeType

    fun write(response: HttpServletResponse, codeFile: CodeFile)

    fun canWrite(codeType: CodeType): Boolean {
        return writeType == codeType
    }

    companion object {
        fun get(codeType: CodeType): CodeFileWriter? {
            val codeFileList = SpringUtil.getBeansOfType(CodeFileWriter::class.java).values
            return codeFileList.first { it.canWrite(codeType) }
        }
    }

}
