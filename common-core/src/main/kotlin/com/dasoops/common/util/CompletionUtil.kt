package com.dasoops.common.util

import cn.hutool.core.date.DateField
import cn.hutool.core.date.DateUtil
import java.util.*

/**
 * 集合补全工具
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/25
 */
object CompletionUtil {
    /**
     * @see com.dasoops.common.util.CompletionUtil.listDataCompletion
     */
    fun <T1> listDataCompletion(
        beginTime: Date, endTime: Date, dateField: DateField, dateList: MutableList<Date>,
        dataList1: MutableList<T1>, defaultValue1: T1
    ) {
        listDataCompletion(beginTime, endTime, dateField, dateList, dataList1, defaultValue1, null, null)
    }

    /**
     * @see com.dasoops.common.util.CompletionUtil.listDataCompletion
     */
    fun <T1, T2> listDataCompletion(
        beginTime: Date, endTime: Date, dateField: DateField, dateList: MutableList<Date>,
        dataList1: MutableList<T1>, defaultValue1: T1,
        dataList2: MutableList<T2>?, defaultValue2: T2
    ) {
        listDataCompletion(
            beginTime,
            endTime,
            dateField,
            dateList,
            dataList1,
            defaultValue1,
            dataList2,
            defaultValue2,
            null,
            null,
        )
    }

    /**
     * @see com.dasoops.common.util.CompletionUtil.listDataCompletion
     */
    fun <T1, T2, T3> listDataCompletion(
        beginTime: Date, endTime: Date, dateField: DateField, dateList: MutableList<Date>,
        dataList1: MutableList<T1>, defaultValue1: T1,
        dataList2: MutableList<T2>?, defaultValue2: T2,
        dataList3: MutableList<T3>?, defaultValue3: T3
    ) {
        listDataCompletion(
            beginTime,
            endTime,
            dateField,
            dateList,
            dataList1,
            defaultValue1,
            dataList2,
            defaultValue2,
            dataList3,
            defaultValue3,
            null,
            null,
        )
    }

    /**
     * @see com.dasoops.common.util.CompletionUtil.listDataCompletion
     */
    fun <T1, T2, T3, T4> listDataCompletion(
        beginTime: Date, endTime: Date, dateField: DateField, dateList: MutableList<Date>,
        dataList1: MutableList<T1>, defaultValue1: T1,
        dataList2: MutableList<T2>?, defaultValue2: T2,
        dataList3: MutableList<T3>?, defaultValue3: T3,
        dataList4: MutableList<T4>?, defaultValue4: T4
    ) {
        listDataCompletion<T1, T2, T3, T4, Any?>(
            beginTime,
            endTime,
            dateField,
            dateList,
            dataList1,
            defaultValue1,
            dataList2,
            defaultValue2,
            dataList3,
            defaultValue3,
            dataList4,
            defaultValue4,
            null,
            null
        )
    }

    /**
     * @see com.dasoops.common.util.CompletionUtil.listDataCompletion
     */
    fun <T1, T2, T3, T4, T5> listDataCompletion(
        beginTime: Date, endTime: Date, dateField: DateField, dateList: MutableList<Date>,
        dataList1: MutableList<T1>, defaultValue1: T1,
        dataList2: MutableList<T2>?, defaultValue2: T2,
        dataList3: MutableList<T3>?, defaultValue3: T3,
        dataList4: MutableList<T4>?, defaultValue4: T4,
        dataList5: MutableList<T5>?, defaultValue5: T5
    ) {
        listDataCompletion(
            beginTime,
            endTime,
            dateField,
            1,
            dateList,
            dataList1,
            defaultValue1,
            dataList2,
            defaultValue2,
            dataList3,
            defaultValue3,
            dataList4,
            defaultValue4,
            dataList5,
            defaultValue5
        )
    }

    /**
     * 集合数据补全
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param dateField 日期字段
     * @param dateStep 日期步进
     * @param dateList 日期集合
     * @param dataList1 数据集合1
     * @param defaultValue1 数据集合1默认值
     * @param dataList2 数据集合2
     * @param defaultValue2 数据集合2默认值
     * @param dataList3 数据集合3
     * @param defaultValue3 数据集合3默认值
     * @param dataList4 数据集合4
     * @param defaultValue4 数据集合4默认值
     * @param dataList5 数据集合5
     * @param defaultValue5 数据集合5默认值
     */
    fun <T1, T2, T3, T4, T5> listDataCompletion(
        beginTime: Date, endTime: Date, dateField: DateField, dateStep: Int, dateList: MutableList<Date>,
        dataList1: MutableList<T1>, defaultValue1: T1,
        dataList2: MutableList<T2>?, defaultValue2: T2,
        dataList3: MutableList<T3>?, defaultValue3: T3,
        dataList4: MutableList<T4>?, defaultValue4: T4,
        dataList5: MutableList<T5>?, defaultValue5: T5
    ) {
        val dateListSize = dateList.size
        //参数检查
        val (finalBeginTime, finalEndTime) = if (beginTime.before(endTime)) {
            DateUtil.truncate(beginTime, dateField) to DateUtil.truncate(endTime, dateField)
        } else {
            DateUtil.truncate(endTime, dateField) to DateUtil.truncate(beginTime, dateField)
        }

        if (dataList1.size != dateListSize
            || (dataList2 != null && dataList2.size != dateListSize)
            || (dataList3 != null && dataList3.size != dateListSize)
            || (dataList4 != null && dataList4.size != dateListSize)
            || (dataList5 != null && dataList5.size != dateListSize)
        ) {
            throw UtilException.CompletionException.LIST_SIZE_NOT_MATCH.get()
        }

        //根据范围日期数判断是否需要补全
        val rangeDateList = DateUtil.rangeToList(finalBeginTime, finalEndTime, dateField, dateStep)
        if (rangeDateList.size == dateListSize) {
            return
        }

        //TODO 缺少日期步进检测,有空一定
        //判断是否同一时间单位
        for (i in rangeDateList.indices) {
            val rangeDate = rangeDateList[i]
            if (dateList.size != i) {
                val date = dateList[i]
                if (isSameDate(rangeDate, date, dateField)) {
                    continue
                }
            }
            dateList.add(i, rangeDate.toJdkDate())
            dataList1.add(i, defaultValue1)
            dataList2?.add(i, defaultValue2)
            dataList3?.add(i, defaultValue3)
            dataList4?.add(i, defaultValue4)
            dataList5?.add(i, defaultValue5)
        }
    }

    private fun isSameDate(date1: Date, date2: Date, dateField: DateField): Boolean {
        return when (dateField) {
            DateField.DAY_OF_YEAR, DateField.DAY_OF_WEEK, DateField.DAY_OF_WEEK_IN_MONTH -> {
                DateUtil.isSameDay(date1, date2)
            }

            DateField.WEEK_OF_YEAR, DateField.WEEK_OF_MONTH -> {
                DateUtil.isSameWeek(date1, date2, true)
            }

            DateField.MONTH -> {
                DateUtil.isSameMonth(date1, date2)
            }

            else -> {
                val beginTime1 = DateUtil.truncate(date1, dateField)
                val beginTime2 = DateUtil.truncate(date2, dateField)
                beginTime1 == beginTime2
            }
        }
    }
}