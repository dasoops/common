package com.dasoops.common.code.ts

import com.dasoops.common.code.CodeFileWriter
import com.dasoops.common.code.entity.CodeFile
import com.dasoops.common.code.entity.CodeType
import org.springframework.stereotype.Component
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

    override fun write(response: HttpServletResponse, codeFile: CodeFile) {
        TODO("Not yet implemented")
    }
}