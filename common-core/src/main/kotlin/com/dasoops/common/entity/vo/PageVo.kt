package com.dasoops.common.entity.vo

import io.swagger.annotations.ApiModelProperty

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
    @ApiModelProperty(value = "总记录数", notes = "总记录数", required = true)
    val total: Int
)