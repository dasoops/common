package com.dasoops.common.entity.param

import cn.hutool.core.date.DateField
import cn.hutool.core.date.DateUtil
import com.dasoops.common.serializer.String2DateTimeConverter
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.*

/**
 * 时间param
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/28
 * @see [TimeParam]
 */
@Tag(name = "base")
@Schema(description = "时间param")
open class TimeParam(
    /**
     * 开始时间
     */
    @field:Schema(description = "开始时间", required = true)
    @JsonDeserialize(converter = String2DateTimeConverter.Jackson::class)
    open var beginTime: Date = DateUtil.date().offset(DateField.DAY_OF_YEAR, -7),

    /**
     * 结束时间
     */
    @field:Schema(description = "结束时间", required = false)
    @JsonDeserialize(converter = String2DateTimeConverter.Jackson::class)
    open var endTime: Date = DateUtil.date()
) {

    operator fun component1() = beginTime
    operator fun component2() = endTime
}