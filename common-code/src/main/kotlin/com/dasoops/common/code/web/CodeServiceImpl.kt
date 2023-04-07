package com.dasoops.common.code.web

import cn.hutool.core.util.ReflectUtil
import cn.hutool.extra.spring.SpringUtil
import com.dasoops.common.code.entity.CodeFile
import com.dasoops.common.code.entity.CodeParam
import com.dasoops.common.util.ConversionService
import io.swagger.v3.oas.models.OpenAPI
import org.springdoc.webmvc.api.OpenApiResource
import org.springframework.stereotype.Service
import java.util.*

/**
 * 代码生成服务实现
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/06
 * @see [CodeServiceImpl]
 */
@Service
class CodeServiceImpl : CodeService {

    override fun getCodeFile(param: CodeParam): CodeFile {
        val openApiResource = SpringUtil.getBean(OpenApiResource::class.java)
        val getOpenApi = ReflectUtil.getMethod(OpenApiResource::class.java, "getOpenApi", Locale::class.java)
        val openApi = ReflectUtil.invoke<OpenAPI>(openApiResource, getOpenApi, Locale.getDefault())
        return ConversionService.convert(openApi, CodeFile::class.java)!!
    }
}