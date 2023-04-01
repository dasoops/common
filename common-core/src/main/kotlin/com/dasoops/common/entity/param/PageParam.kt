package com.dasoops.common.entity.param

import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * 分页参数
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [PageParam]
 */
@Api("base")
@ApiModel(description = "分页参数")
data class PageParam(
    @ApiModelProperty(value = "当前页码", notes = "当前页码", example = "1", required = true)
    var current: Int = 1,

    @ApiModelProperty(value = "每页显示数量", notes = "每页显示数量", example = "10", required = true)
    var size: Int = 10,
)