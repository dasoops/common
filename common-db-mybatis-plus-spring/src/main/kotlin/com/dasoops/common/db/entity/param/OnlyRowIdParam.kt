package com.dasoops.common.db.entity.param

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag

/**
 * rowIdParam
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/31
 * @see [OnlyRowIdParam]
 */
@Tag(name = "base")
@Schema(description = "仅rowIdParam")
data class OnlyRowIdParam(
    /**
     * 主键id
     */
    @field:Schema(description = "主键id", required = true)
    val rowId: Long
)