package com.dasoops.common;

import cn.hutool.core.date.DateField
import cn.hutool.core.date.DatePattern
import cn.hutool.core.date.DateUtil
import com.dasoops.common.entity.param.TimeParam
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

/**
 * 自定义时间param
 * @author DasoopsNicole@Gmail.com
 * @date 2023-04-27
 */
class CustomPatternTimeParam(
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_MINUTE_PATTERN)
    beginTime: Date = DateUtil.date().offset(DateField.DAY_OF_YEAR, -7),
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_MINUTE_PATTERN)
    endTime: Date = DateUtil.date()
) : TimeParam(beginTime, endTime)