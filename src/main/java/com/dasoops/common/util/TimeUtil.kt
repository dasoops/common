package com.dasoops.common.util

import com.dasoops.common.util.entity.dto.TimeDto
import java.util.concurrent.TimeUnit

/**
 * @title TimeUtil
 * @classPath com.dasoops.common.util.TimeUtil
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/10
 * @version 1.0.0
 * @description 时间工具
 */
object TimeUtil {
    /**
     * 分离时间和时间单位数
     *
     * @param timeString 时间字符串
     * @return [TimeDto]
     */
    fun separationUnit(timeString: String): Pair<Long, TimeUnit> {
        val sleepTimeChars = timeString.chars().toArray()
        val timeUnitString = StringBuilder()
        var timeEndIndex = 0
        for (i in sleepTimeChars.indices) {
            val timeChar = sleepTimeChars[i]
            if (timeChar >= '0'.code && timeChar <= '9'.code) {
                timeEndIndex = i + 1
                continue
            }
            timeUnitString.append(timeChar.toChar())
        }

        return timeString.substring(0, timeEndIndex).toLong() to when (timeUnitString.toString()) {
            "m", "min", "minute", "minutes" -> TimeUnit.MINUTES
            "h", "hour" -> TimeUnit.HOURS
            "d", "day", "days" -> TimeUnit.DAYS
            else -> TimeUnit.SECONDS
        }
    }
}