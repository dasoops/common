package com.dasoops.common.code.ts.style

import cn.hutool.core.text.StrPool
import com.dasoops.common.code.entity.Entity
import com.dasoops.common.code.entity.EntityType
import com.dasoops.common.code.entity.In
import com.dasoops.common.code.entity.Param
import com.dasoops.common.code.entity.PropertyType
import com.dasoops.common.code.entity.Request
import com.dasoops.common.code.entity.RequestType

/**
 * 请求方法
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/10
 */
object RequestMethod {
    fun `for`(request: Request, axiosModelName: String) = """
        |${Anno.`for`(request.description)}
        |${methodBody(request, axiosModelName)}
    """.trimMargin() + StrPool.CRLF

    private fun requestType(requestType: RequestType) = when (requestType) {
        RequestType.GET -> "GET"
        RequestType.POST -> "POST"
        RequestType.PUT -> "PUT"
        RequestType.DELETE -> "DELETE"
    }

    private fun methodBody(request: Request, axiosModelName: String) = """
        |export const ${requestName(request)} = function (${methodParam(request.paramList)}) {
        |    return $axiosModelName({
        |        url: "${path(request.path)}",
        |        method: "${requestType(request.type)}",${requestBody(request)}${requestParam(request)}
        |    });
        |}
    """.trimMargin()

    private fun requestName(request: Request): String {
        return request.name ?: "undefined"
    }

    private fun path(path: String) = path.replace("{", "$").replace("}", "")

    private fun methodParam(paramList: Collection<Param>?) =
        paramList?.joinToString(", ") { "${it.name}${require(it.require)}: ${entityType(it)}" } ?: ""


    private fun require(require: Boolean): String {
        return if (!require) {
            "?"
        } else {
            ""
        }
    }

    private fun entityType(param: Param): String {
        return when (param.entityType) {
            PropertyType.DATE -> "Date"
            PropertyType.STRING -> "string"
            PropertyType.INT, PropertyType.NUMBER -> "number"
            PropertyType.BOOLEAN -> "boolean"
            PropertyType.ARRAY -> entityType(param.entity) + "[]"
            PropertyType.OBJECT -> entityType(param.entity)
        }
    }

    private fun entityType(entity: Entity?): String {
        entity ?: return "any"
        return when (entity.type) {
            EntityType.INT -> "number"
            EntityType.STRING -> "string"
            EntityType.ARRAY -> entity.name + "[]"
            EntityType.OBJECT -> entity.name
        }
    }

    private fun requestBody(request: Request) =
        if (RequestType.GET == request.type) {
            ""
        } else {
            request.paramList?.firstOrNull { it.`in` == In.BODY }?.run { "${StrPool.CRLF}        data: ${this.name}," } ?: ""
        }

    private fun requestParam(request: Request) =
        request.paramList?.firstOrNull { it.`in` == In.QUERY }?.run { "${StrPool.CRLF}        param: ${this.name}," } ?: ""

}