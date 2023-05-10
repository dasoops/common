package com.dasoops.common.entity.param

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag

/**
 * 分页参数
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [PageParam]
 */
@Tag(name = "base")
@Schema(description = "分页参数")
data class PageParam(
    /**
     * 当前页码
     */
    @field:Schema(description = "当前页码", example = "1", required = true)
    var current: Int = 1,

    /**
     * 每页显示数量
     */
    @field:Schema(description = "每页显示数量", example = "10", required = true)
    var size: Int = 10,
)