package com.dasoops.common.code.entity

import io.swagger.models.Path

/**
 * swagger路径全部信息
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [SwaggerPath]
 */
class SwaggerPath(val path: String, val info: Path) {
    constructor(pathEntry: Map.Entry<String, Path>) : this(pathEntry.key, pathEntry.value)
}

