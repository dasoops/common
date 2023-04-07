package com.dasoops.common.code.converter

import com.dasoops.common.code.entity.Request
import com.dasoops.common.code.entity.SwaggerPath
import org.springframework.core.convert.converter.Converter

class SwaggerPath2RequestConverter : Converter<SwaggerPath, Request> {
    override fun convert(source: SwaggerPath): Request? {
        TODO("Not yet implemented")
    }

}
