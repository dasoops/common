package com.dasoops.common.db.entity.param

import com.dasoops.common.db.entity.dbo.base.BaseDo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * 时间param
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/28
 * @see [TimeParam]
 */
@Api("base")
@ApiModel(description = "时间param")
data class TimeParam<T : BaseDo>(
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", notes = "开始时间", required = true)
    val beginTime: String? = null,

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", notes = "结束时间", required = false)
    val endTime: String? = null
)