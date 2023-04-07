package com.dasoops.common.code.converter

import com.dasoops.common.code.entity.CodeFile
import io.swagger.v3.oas.models.OpenAPI
import org.springframework.core.convert.converter.Converter

class OpenApi2CodeFileConverter : Converter<OpenAPI, CodeFile> {
    override fun convert(source: OpenAPI): CodeFile {
        TODO("Not yet implemented")
    }

}
