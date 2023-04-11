package com.dasoops.common.code.web

import cn.hutool.core.util.ReflectUtil
import cn.hutool.extra.spring.SpringUtil
import com.dasoops.common.code.converter.CodeFileBuilder
import com.dasoops.common.code.entity.CodeFileZip
import com.dasoops.common.code.entity.CodeParam
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

    override fun getCodeFile(param: CodeParam): CodeFileZip {
        val openApiResource = SpringUtil.getBean(OpenApiResource::class.java)
        val getOpenApi = ReflectUtil.getMethod(OpenApiResource::class.java, "getOpenApi", Locale::class.java)
        val openApi = ReflectUtil.invoke<OpenAPI>(openApiResource, getOpenApi, Locale.getDefault())
        return CodeFileBuilder(
            source = openApi,
            another = param.another,
            axiosModelName = param.axiosModelName,
            excludeRequestPrefix = param.excludeRequestPrefix,
            entityFileBasePath = param.entityFileBasePath,
            requestFileBasePath = param.requestFileBasePath,
            entityFileBaseImport = param.entityFileBaseImport,
            requestFileBaseImport = param.requestFileBaseImport
        ).build()
    }
}