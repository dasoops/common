package com.dasoops.common.entity.param

import cn.hutool.core.date.DateField
import cn.hutool.core.date.DateUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*

/**
 * 时间param
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/28
 * @see [TimeParam]
 */
@Api("base")
@ApiModel(description = "时间param")
data class TimeParam(
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", notes = "开始时间", required = true)
    val beginTime: Date = DateUtil.date().offset(DateField.DAY_OF_YEAR, -7),

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", notes = "结束时间", required = false)
    val endTime: Date = DateUtil.date()
)