package com.dasoops.common.code.web

import com.dasoops.common.code.CodeException
import com.dasoops.common.code.CodeFileWriter
import com.dasoops.common.code.entity.CodeParam
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@Tag(name = "代码生成")
@RequestMapping("code")
class CodeWebApi(
    val service: CodeService
) {

    @GetMapping("down")
    @Operation(summary = "下载代码文件")
    fun down(response: HttpServletResponse, param: CodeParam = CodeParam()) {
        val codeFile = service.getCodeFile(param)
        val writer = CodeFileWriter.get(param.codeType) ?: throw CodeException.UNDEFINED_CONVERT.get()
        writer.write(response, codeFile)
    }

}