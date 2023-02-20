package com.dasoops.common.util;

import com.dasoops.common.util.entity.TimeDto;

import java.util.concurrent.TimeUnit;

/**
 * @title TimeUtil
 * @classPath com.dasoops.common.util.TimeUtil
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/10
 * @version 1.0.0
 * @description 时间工具
 */
public class TimeUtil {

    /**
     * 分离时间和时间单位数
     *
     * @param timeString 时间字符串
     * @return {@link TimeDto}
     */
    public static TimeDto cutTimeUnitAndTimeCount(String timeString) {
        int[] sleepTimeChars = timeString.chars().toArray();
        StringBuilder timeUnitString = new StringBuilder();
        int timeEndIndex = 0;
        for (int i = 0; i < sleepTimeChars.length; i++) {
            int timeChar = sleepTimeChars[i];
            if (timeChar >= '0' && timeChar <= '9') {
                timeEndIndex = i + 1;
                continue;
            }
            timeUnitString.append((char) timeChar);
        }

        TimeUnit timeUnit;
        switch (timeUnitString.toString()) {
            case "m", "min", "minute", "minutes" -> timeUnit = TimeUnit.MINUTES;
            case "h", "hour" -> timeUnit = TimeUnit.HOURS;
            case "d", "day", "days" -> timeUnit = TimeUnit.DAYS;
            default -> timeUnit = TimeUnit.SECONDS;
        }
        Long count = Long.valueOf(timeString.substring(0, timeEndIndex));

        TimeDto timeDto = new TimeDto();
        timeDto.setCount(count);
        timeDto.setTimeUnit(timeUnit);
        return timeDto;
    }

}
