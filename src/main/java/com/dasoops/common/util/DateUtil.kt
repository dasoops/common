package com.dasoops.common.util

import cn.hutool.core.date.DateUtil
import java.util.*

/**
 * 日期工具
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/25
 */
object DateUtil : DateUtil() {
    fun empty(): Date {
        return date(0)
    }
}