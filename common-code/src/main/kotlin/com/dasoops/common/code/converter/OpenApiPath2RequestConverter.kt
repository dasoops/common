package com.dasoops.common.code.converter

import com.dasoops.common.code.entity.In
import com.dasoops.common.code.entity.OpenApiPath
import com.dasoops.common.code.entity.Param
import com.dasoops.common.code.entity.Property
import com.dasoops.common.code.entity.Request
import com.dasoops.common.code.entity.RequestType
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.parameters.RequestBody
import org.slf4j.LoggerFactory

/**
 * ApiPath2Request转换器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [OpenApiPath2RequestConverter]
 */
object OpenApiPath2RequestConverter {

    private val log = LoggerFactory.getLogger(javaClass)

    fun convert(source: OpenApiPath): List<Request> {
        val (path, pathItem) = source

        return arrayListOf(
            buildSingle(path, pathItem.get, RequestType.GET, pathItem.description),
            buildSingle(path, pathItem.post, RequestType.POST, pathItem.description),
            buildSingle(path, pathItem.delete, RequestType.DELETE, pathItem.description),
            buildSingle(path, pathItem.put, RequestType.PUT, pathItem.description),
        ).filterNotNull()
    }

    private fun paramList(parameters: List<Parameter>?, requestBody: RequestBody?): List<Param>? {
        val paramList = parameters?.mapNotNull {
            Param(
                name = it.name,
                `in` = when (it.`in`) {
                    "query" -> In.QUERY
                    "path" -> In.PATH
                    else -> {
                        log.warn("param in ${it.`in`} undefined")
                        return@mapNotNull null
                    }
                },
                require = it.required,
                originalProperty = Property.from(it.schema, it.required),
            )
        } ?: emptyList()

        requestBody?.content?.values?.first()?.let {
            return paramList.toMutableList().apply {
                add(
                    Param(
                        name = "param",
                        `in` = In.BODY,
                        require = requestBody.required,
                        originalProperty = Property.from(it.schema, false),
                    )
                )
            }.ifEmpty { null }
        }
        return paramList.ifEmpty { null }
    }

    private fun buildSingle(path: String, operation: Operation?, type: RequestType?, group: String?): Request? {
        operation ?: return null
        val schema: Schema<*>? = operation.responses["200"]!!.content?.get("*/*")?.schema
        val property = if (schema != null) {
            Property.from(schema, false)
        } else {
            null
        }
        return Request(
            group = group ?: "default",
            type = type!!,
            path = path,
            paramList = paramList(operation.parameters, operation.requestBody),
            result = property,
            name = operation.description,
            description = operation.summary,
        )
    }
}
