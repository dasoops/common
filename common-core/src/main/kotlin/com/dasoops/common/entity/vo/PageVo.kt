package com.dasoops.common.entity.vo

import io.swagger.v3.oas.annotations.media.Schema


/**
 * 页面vo
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/31
 * @see [PageVo]
 */
data class PageVo(

    /**
     * 总记录数
     */
    @Schema(description = "总记录数", required = true)
    val total: Int
)