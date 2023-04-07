package com.dasoops.common.code.entity

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag

/**
 * 获取代码参数
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/06
 * @see [CodeParam]
 */
@Tag(name = "code")
@Schema(description = "获取代码参数")
data class CodeParam(
    /**
     * 代码类型
     */
    @Schema(description = "代码类型", required = true)
    val codeType: CodeType = CodeType.TS,
)