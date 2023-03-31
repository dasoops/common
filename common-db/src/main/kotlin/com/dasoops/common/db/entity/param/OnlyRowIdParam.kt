package com.dasoops.common.db.entity.param

import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * rowIdParam
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/31
 * @see [OnlyRowIdParam]
 */
@Api("base")
@ApiModel(description = "仅rowIdParam")
data class OnlyRowIdParam(
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = true)
    val rowId: Long
)