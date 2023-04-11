package com.dasoops.common.code.entity

import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.media.Schema

/**
 * openApi路径信息
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [OpenApiPath]
 */
data class OpenApiPath(val path: String, val pathItem: PathItem) {
    constructor(pathEntry: Map.Entry<String, PathItem>) : this(pathEntry.key, pathEntry.value)
}

/**
 * openApi实体信息
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [OpenApiSchema]
 */
data class OpenApiSchema(val path: String, val info: Schema<*>) {
    constructor(schemeEntry: Map.Entry<String, Schema<*>>) : this(schemeEntry.key, schemeEntry.value)
}